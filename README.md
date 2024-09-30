## Projet 1 : Gestion des Sites

Ce projet vise à créer une table dans une base de données pour gérer plusieurs sites. Les sites à suivre sont El Jadida, Marrakech, et Safi. Les informations relatives à ces sites seront stockées dans une table avec les colonnes suivantes :

- *id* : Identifiant unique pour chaque site (int)
- *nom* : Nom du site (string)

### Fonctionnalités

- Création de la base de données.
- Création d'une table pour stocker les sites.
- Insertion des sites dans la table.

## Projet 2 : Suivi des Scripts des Développeurs

Ce projet consiste à suivre la réalisation des scripts des développeurs d'un projet. Un rapport hebdomadaire est élaboré par le chef de projet, qui consigne chaque jour le nombre de scripts réalisés par les différents développeurs. Ces informations sont stockées dans un Système de Gestion de Base de Données (SGBD) au sein d'une table.

### Structure de la Table

La table dans la base de données est structurée comme suit :

- *Développeur* : Nom du développeur (string)
- *Jour* : Jour de la semaine (string)
- *NBScripts* : Nombre de scripts réalisés (int)

### Exemple de Données

Les données à insérer dans la table sont les suivantes :

| Développeur | Jour     | NBScripts |
|-------------|----------|-----------|
| ALAMI       | Lundi    | 1         |
| WAFI        | Lundi    | 2         |
| SALAMI      | Mardi    | 9         |
| SAFI        | Mardi    | 2         |
| ALAMI       | Mardi    | 2         |
| SEBIHI      | Mercredi | 2         |
| WAFI        | Jeudi    | 3         |
| ALAOUI      | Vendredi | 9         |
| WAFI        | Vendredi | 3         |
| SEBIHI      | Vendredi | 4         |

### Fonctionnalités

- Création d'une base de données.
- Création d'une table pour stocker les informations des scripts réalisés par les développeurs.
- Insertion des données sur les scripts réalisés.
- Génération de rapports hebdomadaires sur les réalisations des développeurs.

## Conclusion

Ces deux projets permettent de gérer efficacement les informations sur les sites et de suivre les contributions des développeurs. Ils facilitent la prise de décision et améliorent la gestion des ressources au sein des projets.
