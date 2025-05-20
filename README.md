**# ♻️ SmartRecycle

<sub>Application mobile de tri intelligent par IA</sub>

## 📋 Description
Solution de classification des déchets en temps réel via un modèle TensorFlow Lite embarqué, fonctionnant hors ligne. L'application s'intègre avec un backend Spring Boot pour fournir des consignes de tri adaptées aux réglementations locales.

## ✨ Fonctionnalités principales
- **Classification visuelle** des déchets sans connexion Internet
- **Base de données** des règles locales de recyclage (MySQL)
- **API sécurisée** en HTTPS pour les mises à jour
- **Léger et rapide** (optimisé pour mobile)

## 🏗 Architecture
## 🏗 Architecture

```mermaid
graph TD
    %% Mobile Components
    A[App Android] -->|Capture Image| B[TensorFlow Lite]
    A -->|HTTPS REST| C[Spring Boot]
    
    %% Backend Components
    C -->|JPA/Hibernate| D[(MySQL)]
    D -->|Admin| E[phpMyAdmin]
    
    %% Data Flow
    B -->|Classification| A
    C -->|Consignes| A
    
    %% Style
    style A fill:#98ff98,stroke:#333  <!-- Vert pour mobile -->
    style C fill:#ff9999,stroke:#333 <!-- Rouge pour backend -->
    style D fill:#9999ff,stroke:#333 <!-- Bleu pour base de données -->i]



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

**                   

https://github.com/user-attachments/assets/023100eb-9e9c-4f5e-8aeb-93168cbf09a4

