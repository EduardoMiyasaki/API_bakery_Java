package com.example.springboot.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BakeryRecordDto(@NotBlank String nameProduct , @NotNull int price_in_cents) {
    // O recordDto é uma prática recomendada no design de software para a transferência de dados de maneira eficiente,
    // segura e organizada. Ele ajuda a manter a clareza e a modularidade do código,
    // facilitando a manutenção e a escalabilidade das aplicações.
}
