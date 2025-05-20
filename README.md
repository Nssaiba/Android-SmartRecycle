<!-- Bannière avec badges -->
<div align="center">
  <h1><big>♻️ SmartRecycle</big></h1>
  <h3><sub>L'application de tri intelligent par IA</sub></h3>
</div>

---

## 📱 **Fonctionnalités**
<sub>> **Technologie clé** : TensorFlow Lite embarqué</sub>

- <sup>📸</sup> **Classification hors ligne** des déchets via l'appareil photo  
- <sup>🗂️</sup> **Règles locales** stockées en base de données (MySQL)  
- <sup>🛡️</sup> Communication sécurisée **HTTPS**  
- <sup>🔄</sup> Adaptabilité aux réglementations locales  
- <sup>⚡</sup> Performance optimisée pour mobiles  

---

## 🎯 **Présentation**
SmartRecycle est une application mobile intelligente qui facilite le tri des déchets grâce à un modèle de vision par ordinateur embarqué basé sur **TensorFlow Lite**. Elle permet :
- De classifier instantanément les déchets **sans connexion Internet**
- D'afficher des consignes de tri adaptées aux règles locales
- De s'intégrer parfaitement aux contraintes environnementales

---

## 🛠️ **Architecture Technique**
```mermaid
graph TD
    A[Application Android] -->|HTTPS| B[Backend Spring Boot]
    B --> C[(Base de données MySQL)]
    C --> D[Interface phpMyAdmin]
    A -->|Modèle embarqué| E[TensorFlow Lite]
    F[Dashboard Admin] --> B










https://github.com/user-attachments/assets/c81664c0-fe75-473c-a575-cb7674848bd2

