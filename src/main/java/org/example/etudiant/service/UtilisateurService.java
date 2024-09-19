package org.example.etudiant.service;

import org.example.etudiant.dao.UtilisateurRepository;
import org.example.etudiant.dto.UtilisateurDtoReceive;
import org.example.etudiant.entity.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UtilisateurService {

    // ========== Propriétés ==========

    @Autowired
    private UtilisateurRepository utilisateurRepository;


    // ========== Constructeur ==========

    public Utilisateur getById(int id) {
        return utilisateurRepository.findById(id).orElse(null);
    }


    // ========== Méthodes ==========

    public Utilisateur create(UtilisateurDtoReceive utilisateurDtoReceive) {
        Utilisateur utilisateurCreated = Utilisateur.builder()
                .username(utilisateurDtoReceive.getUsername())
                .password(utilisateurDtoReceive.getPassword())
                .build();

        return utilisateurRepository.save(utilisateurCreated);
    }

//    public Utilisateur save(UtilisateurDtoReceive utilisateur) {
//        return utilisateurRepository.save(utilisateur);
//    }

    public List<Utilisateur> getAll() {
        return (List<Utilisateur>) utilisateurRepository.findAll();
    }

}
