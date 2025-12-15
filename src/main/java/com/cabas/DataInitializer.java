package com.cabas;

import com.cabas.entity.User;
import com.cabas.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;

    public DataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        System.out.println("--- Démarrage de l'initialisation des utilisateurs de test ---");

        // Transporteur pour les tests de POST /trips
        User carrier = User.builder()
                .firstName("Transporteur").lastName("Test").email("carrier@test.com").password("pass").phoneNumber("0601").averageRating(4.5)
                .build();

        // Expéditeur pour les tests de recherche (F-2.4)
        User sender = User.builder()
                .firstName("Expediteur").lastName("Test").email("sender@test.com").password("pass").phoneNumber("0701").averageRating(4.8)
                .build();

        userRepository.saveAll(List.of(carrier, sender));

        System.out.println("✅ Utilisateurs de test créés. Transporteur ID: 99, Expéditeur ID: 88.");
    }
}