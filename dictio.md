### Table : Utilisateurs

| Attribut           | Type de données | Description                                    |
|--------------------|-----------------|------------------------------------------------|
| ID                 | Entier          | Clé primaire, identifiant de l'utilisateur     |
| Nom d'utilisateur  | Chaîne de texte | Nom d'utilisateur de l'utilisateur             |
| Adresse e-mail     | Chaîne de texte | Adresse e-mail de l'utilisateur                |
| Mot de passe       | Chaîne de texte | Mot de passe hashé de l'utilisateur            |
| Type d'utilisateur | Chaîne de texte | Type de l'utilisateur (propriétaire/locataire) |

### Table : Propriétaires

| Attribut            | Type de données | Description                               |
|---------------------|-----------------|-------------------------------------------|
| ID                  | Entier          | Clé primaire, identifiant du propriétaire |
| Prénom              | Chaîne de texte | Prénom du propriétaire                    |
| Nom                 | Chaîne de texte | Nom du propriétaire                       |
| Numéro de téléphone | Chaîne de texte | Numéro de téléphone du propriétaire       |

### Table : Maisons

| Attribut           | Type de données | Description                                         |
|--------------------|-----------------|-----------------------------------------------------|
| ID                 | Entier          | Clé primaire, identifiant de la maison              |
| Titre de l'annonce | Chaîne de texte | Titre de l'annonce de la maison                     |
| Description        | Chaîne de texte | Description détaillée de la maison                  |
| Nombre de chambres | Entier          | Nombre de chambres de la maison                     |
| Superficie         | Réel            | Superficie de la maison en m²                       |
| Adresse            | Chaîne de texte | Adresse de la maison                                |
| Prix de location   | Réel            | Prix de location de la maison en CFA                |
| Statut             | Chaîne de texte | Statut de la maison (disponible/loué/suspendu/etc.) |

### Table : Évaluations

| Attribut           | Type de données | Description                                    |
|--------------------|-----------------|------------------------------------------------|
| ID                 | Entier          | Clé primaire, identifiant de l'évaluation      |
| Note               | Entier          | Note attribuée à la maison (de 1 à 5)          |
| Commentaire        | Chaîne de texte | Commentaire de l'utilisateur sur la maison     |

### Table : Favoris

| Attribut  | Type de données | Description                          |
|-----------|-----------------|--------------------------------------|
| ID        | Entier          | Clé primaire                         |

### Table : Photos

| Attribut           | Type de données | Description                                |
|--------------------|-----------------|--------------------------------------------|
| ID                 | Entier          | Clé primaire, identifiant de la photo      |
| URL de la photo    | Chaîne de texte | URL de la photo associée à la maison       |

### Table : Localisations

| Attribut                    | Type de données | Description                                     |
|-----------------------------|-----------------|-------------------------------------------------|
| ID                          | Entier          | Clé primaire, identifiant de la localisation    |
| Nom de la zone géographique | Chaîne de texte | Nom de la zone géographique                     |
| Coordonnées géographiques   | Chaîne de texte | Coordonnées géographiques (latitude, longitude) |

### Table : TypesLogement

| Attribut                | Type de données | Description                                   |
|-------------------------|-----------------|-----------------------------------------------|
| ID                      | Entier          | Clé primaire, identifiant du type de logement |
| Nom du type de logement | Chaîne de texte | Nom du type de logement                       |

### Table : Messages

| Attribut           | Type de données | Description                                |
|--------------------|-----------------|--------------------------------------------|
| ID                 | Entier          | Clé primaire, identifiant du message       |
| Contenu du message | Chaîne de texte | Contenu du message                         |
| Date d'envoi       | Date            | Date d'envoi du message                    |

### Table : Statistiques

| Attribut          | Type de données | Description                                      |
|-------------------|-----------------|--------------------------------------------------|
| ID                | Entier          | Clé primaire, identifiant des statistiques       |
| Nombre de vues    | Entier          | Nombre de vues de la maison                      |
| Nombre de favoris | Entier          | Nombre de fois où la maison a été mise en favori |
