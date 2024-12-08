package org.example.my_project.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.my_project.dto.request.UserRequest;
import org.example.my_project.dto.response.UserResponse;
import org.example.my_project.entities.User;
import org.example.my_project.exception.AppException;
import org.example.my_project.exception.ErrorCode;
import org.example.my_project.mapper.UserMapper;
import org.example.my_project.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    FileStorageService fileStorageService;

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
        userRepository.save(user);

        return userMapper.toUserResponse(user);
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse)
                .collect(Collectors.toList());

    }

    public UserResponse getUserById(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        return userMapper.toUserResponse(user);
    }

    public UserResponse updateUser(String id, UserRequest userRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

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
        User updatedUser = userRepository.save(user);
        return userMapper.toUserResponse(updatedUser);
    }


    public void delete(String id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        userRepository.delete(user);
    }

}
