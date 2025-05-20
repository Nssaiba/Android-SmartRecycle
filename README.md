# ♻️ SmartRecycle

<sub>Application mobile de tri intelligent par IA</sub>

## 📋 Description
Solution de classification des déchets en temps réel via un modèle TensorFlow Lite embarqué, fonctionnant hors ligne. L'application s'intègre avec un backend Spring Boot pour fournir des consignes de tri adaptées aux réglementations locales.

## ✨ Fonctionnalités principales
- **Classification visuelle** des déchets sans connexion Internet
- **Base de données** des règles locales de recyclage (MySQL)
- **API sécurisée** en HTTPS pour les mises à jour
- **Léger et rapide** (optimisé pour mobile)

## 🏗 Architecture

App Android]
│ (TensorFlow Lite)
↓
[Spring Boot] ← HTTPS → [Client Mobile]
│
↓ (JDBC)
[MySQL Database]
│
↓ (Admin)
[phpMyAdmin]



### Composants :
1. **Couche Mobile** :
   - Module IA embarqué (TFLite)
   - Appels API sécurisés

2. **Couche Backend** :
   - Contrôleurs Spring Boot
   - Service de règles métier
   - Repository JPA

3. **Data Layer** :
   - Tables MySQL : 
     - `recycling_rules`
     - `material_categories`

## 🛠 Stack technique
### Application mobile
- Android (Kotlin/Java)
- TensorFlow Lite (modèle embarqué)

### Backend
- Spring Boot (Java)
- MySQL (phpMyAdmin en option)

### Outils
- Postman (tests API)
- Git/GitHub (gestion de version)

## 🔧 Installation
1. Cloner le dépôt :
```bash
git clone https://github.com/votreuser/SmartRecycle.git
cd SmartRecycle
https://github.com/user-attachments/assets/c81664c0-fe75-473c-a575-cb7674848bd2

