# Gestore Progetti Calendario

## Requisiti

- **Java (JDK):** 21.0.9 (Temurin / OpenJDK 21 LTS)
- **Gradle:** 8.14
- **JavaFX:** 21.0.2
---

## Installazione

Clona la repository ed entra nella cartella del progetto:

```bash
git clone git@github.com:DaniloUnicam/GestoreProgettiCalendario.git
cd GestoreProgettiCalendario
````

---

## Installare Java JDK 21 (Windows)

### 1) Installazione via winget

```powershell
winget install EclipseAdoptium.Temurin.21.JDK
```

### 2) Verifica versione

Chiudi e riapri il terminale, poi:

```bash
java -version
```

Dovresti vedere qualcosa di simile:

```text
openjdk 21.0.9 2025-10-21 LTS
OpenJDK Runtime Environment Temurin-21.0.9+10 (build 21.0.9+10-LTS)
OpenJDK 64-Bit Server VM Temurin-21.0.9+10 (build 21.0.9+10-LTS, mixed mode, sharing)
```

Se **non** vedi `openjdk 21.x.x`, molto probabilmente Java **non è nel PATH** oppure `JAVA_HOME` non punta alla versione corretta.

---

## Impostare JAVA_HOME e PATH (Windows)

1. Apri **PowerShell come Amministratore**.

2. Individua la cartella del JDK installato:

```powershell
Get-ChildItem "C:\Program Files\Eclipse Adoptium"
```

Esempio output:

```text
Directory: C:\Program Files\Eclipse Adoptium

Mode                 LastWriteTime         Length Name
----                 -------------         ------ ----
d-----          2/4/2026   5:52 PM                jdk-21.0.9.10-hotspot
```

La parte che ci interessa è la cartella, ad es.: `jdk-21.0.9.10-hotspot`.

3. Imposta `JAVA_HOME`:

```powershell
setx /M JAVA_HOME "C:\Program Files\Eclipse Adoptium\jdk-21.0.9.10-hotspot"
```

4. Aggiungi Java al `Path` (senza perdere il Path esistente):

```powershell
setx /M Path "%Path%;%JAVA_HOME%\bin"
```

5. Chiudi e riapri il terminale, poi ricontrolla:

```bash
java -version
```

---

## Gradle (configurazione progetto)

### 1) Impostare il JDK per Gradle

Apri `gradle.properties` e controlla la riga:

```properties
org.gradle.java.home=...
```

In questo caso, se il JDK è quello di Adoptium, imposta:

```properties
org.gradle.java.home=C:/Program Files/Eclipse Adoptium/jdk-21.0.9.10-hotspot
```

### 2) Verifica Gradle Wrapper

Dalla root del progetto:

```powershell
.\gradlew --version
```

---

## Compilare ed eseguire il progetto

Dalla root del progetto:

```powershell
.\gradlew run
```