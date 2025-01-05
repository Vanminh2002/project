package org.example.my_project.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.my_project.dto.request.UserRequest;
import org.example.my_project.dto.response.UserResponse;
import org.example.my_project.entities.User;
import org.example.my_project.enums.Role;
import org.example.my_project.exception.AppException;
import org.example.my_project.exception.ErrorCode;
import org.example.my_project.mapper.UserMapper;
import org.example.my_project.repository.UserRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    FileStorageService fileStorageService;
    PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserRequest userRequest) {
        if (userRepository.existsByUsername(userRequest.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTS);
//            throw new RuntimeException("ErrorCode.USER_EXISTS");
        }
        String imageUrl = null;
        if (userRequest.getImageFile() != null) {
            try {
                // Lưu file và lấy URL
                imageUrl = fileStorageService.saveFile("user", userRequest.getImageFile());
            } catch (IOException e) {
                throw new AppException(ErrorCode.UPLOADING_EXCEPTION);
            }
        }
        User user = userMapper.toUser(userRequest);
        user.setImageUrl(imageUrl);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
//        user.setRoles(roles);
        userRepository.save(user);

        return userMapper.toUserResponse(user);
    }

    // kiểm tra trước khi gọi
    @PreAuthorize("hasRole('USER')")
    public List<UserResponse> getAllUsers() {
        log.info("In method getAllUsers");
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse)
                .collect(Collectors.toList());

    }

    // khác biệt với preAuth là kiểu tra sau khi vào hàm
    @PostAuthorize("hasRole('ADMIN')")
    // đúng người nào thì dùng của người đó
//    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse getUserById(String id) {
        log.info("In method getUserById");
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        return userMapper.toUserResponse(user);
    }

    public UserResponse updateUser(String id, UserRequest userRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        if (userRepository.existsByUsername(userRequest.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTS);
        }
        userMapper.updateUser(user, userRequest);
        String imageUrl = user.getImageUrl();
        if (user.getImageUrl() != null) {
            try {
                imageUrl = fileStorageService.replaceFile("user", user.getImageUrl(), userRequest.getImageFile());
            } catch (IOException e) {
                throw new AppException(ErrorCode.UPLOADING_EXCEPTION);
            }
        }
        user.setImageUrl(imageUrl);
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        User updatedUser = userRepository.save(user);
        return userMapper.toUserResponse(updatedUser);
    }


    public void delete(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        userRepository.delete(user);
    }

    public UserResponse getMyInfo() {
//        lấy user hiện tại
        var context = SecurityContextHolder.getContext();
//        từ context hiện tại ta lấy name của người đăng nhập
        String name = context.getAuthentication().getName();
//        từ name của người đăng nhập ta lấy user từ userRepository
        User user = userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        return userMapper.toUserResponse(user);
    }

    public UserMapper getUserMapper() {
        return userMapper;
    }
}
