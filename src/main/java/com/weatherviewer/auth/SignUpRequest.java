package com.weatherviewer.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignUpRequest(@NotBlank(message = "Имя пользователя не может быть пустым")
                            @Size(min = 3, max = 20, message = "Длина логина от 3 до 20 символов")
                            String login,
                            @NotBlank(message = "Пароль не может быть пустым")
                            @Size(min = 3, max = 20, message = "Длина пароля от 3 до 20 символов")
                            String password) {
}
