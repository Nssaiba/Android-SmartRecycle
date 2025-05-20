<!-- Bannière avec badges -->
<div align="center">
  <h1><big>♻️ SmartRecycle</big></h1>
  <h3><sub>L'application de tri intelligent par IA</sub></h3>
  
  [![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)
  [![Android](https://img.shields.io/badge/Android-3DDC84?logo=android)](https://developer.android.com)
  [![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?logo=spring)](https://spring.io)
</div>

---

## 📱 **Fonctionnalités**
<sub>> **Technologie clé** : TensorFlow Lite embarqué</sub>

- <sup>📸</sup> **Classification hors ligne** des déchets via l'appareil photo  
- <sup>🗂️</sup> **Règles locales** stockées en base de données (MySQL)  
- <sup>🛡️</sup> Communication sécurisée **HTTPS**  
- <sup>👨‍💼</sup> **Dashboard admin** pour gérer les consignes  

---

## 🛠️ **Stack Technique**
```mermaid
graph LR
    A[App Android] -->|HTTPS| B[Spring Boot]
    B --> C[(MySQL)]
    C --> D[phpMyAdmin]
    A --> E[TensorFlow Lite]
SmartRecycle est une application mobile intelligente qui facilite le tri des déchets grâce à un modèle de vision par ordinateur embarqué basé sur TensorFlow Lite. Elle permet à l’utilisateur de capturer une image d’un déchet pour en obtenir instantanément la classification, même sans connexion Internet. L’application s’appuie sur un backend sécurisé développé en Spring Boot, qui fournit des consignes de tri adaptées, stockées dans une base de données MySQL administrée via phpMyAdmin. L’ensemble du système est conçu pour être léger, rapide et adaptable aux règles locales de recyclage, combinant performance, accessibilité et respect des contraintes environnementales.

Technologies et outils utilisés dans SmartRecycle :

Mobile & Intelligence Artificielle :
Android (Java/Kotlin), TensorFlow Lite, Google Colab

Backend :
Spring Boot (Java)

Base de données :
MySQL, administrée via phpMyAdmin

Outils de test et d’analyse :
Postman

Déploiement & gestion de version :
Git, GitHub










https://github.com/user-attachments/assets/c81664c0-fe75-473c-a575-cb7674848bd2

