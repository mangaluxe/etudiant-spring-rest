package org.example.etudiant.controller;

import org.example.etudiant.dto.UtilisateurDtoReceive;
import org.example.etudiant.dto.UtilisateurDtoSend;
import org.example.etudiant.entity.Utilisateur;
import org.example.etudiant.service.UtilisateurService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("api/utilisateur/")
public class UtilisateurController {

    // ========== Propriétés ==========

    private UtilisateurService utilisateurService;


    // ========== Constructeur ==========

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }


    // ========== Méthodes ==========

    @GetMapping
    public ResponseEntity<List<UtilisateurDtoSend>> getData() {
        List<Utilisateur> utilisateurs = utilisateurService.getAll();
        List<UtilisateurDtoSend> utilisateurDtoSends = new ArrayList<>();

        for (Utilisateur utilisateur : utilisateurs) {
            utilisateurDtoSends.add(new UtilisateurDtoSend(utilisateur.getId(), utilisateur.getUsername()));
        }

        return ResponseEntity.ok(utilisateurDtoSends);
    }

    @GetMapping("{id}")
    public ResponseEntity<UtilisateurDtoSend> getDataById(@PathVariable int id) {
        Utilisateur utilisateur = utilisateurService.getById(id);

//        UtilisateurDtoSend utilisateurDtoSend = new UtilisateurDtoSend(id, utilisateur.getUsername());
        UtilisateurDtoSend utilisateurDtoSend = new UtilisateurDtoSend(utilisateur.getId(), utilisateur.getUsername());

        return ResponseEntity.ok(new UtilisateurDtoSend(utilisateur.getId(), utilisateur.getUsername()));
    }

    @PostMapping
    public ResponseEntity<UtilisateurDtoSend> registerUtilisateur(@Validated @RequestBody UtilisateurDtoReceive utilisateurDtoReceive) {
//        Utilisateur utilisateur = utilisateurService.save(utilisateurDtoReceive);
        Utilisateur utilisateur = utilisateurService.create(utilisateurDtoReceive);

        UtilisateurDtoSend utilisateurDtoSend = new UtilisateurDtoSend(utilisateur.getId(), utilisateur.getUsername());

//        return ResponseEntity.ok(utilisateurDtoSend);
        return new ResponseEntity<>(utilisateurDtoSend, HttpStatus.CREATED);

    }

}
