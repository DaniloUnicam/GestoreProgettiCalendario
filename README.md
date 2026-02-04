# Gestore Progetti Calendario

Small Java project — quick setup instructions to run locally.

---

## Prerequisites

- Java 21 (JDK 21.x)
- Gradle (use the included Gradle wrapper)
- Windows example commands shown below; Unix/macOS equivalents are also provided.

---

## 1. Install Java JDK 21

Windows (winget):
```powershell
winget install EclipseAdoptium.Temurin.21.JDK
```

macOS (Homebrew):
```bash
brew install temurin21
```

Linux (example with apt for Debian/Ubuntu):
```bash
sudo apt update
sudo apt install -y temurin-21-jdk
```

Verify installation:
```bash
java --version
# Expected: 21.x (e.g. 21.0.9)
```

---

## 2. Configure JAVA_HOME (Windows)

1. Press Start → search "Environment variables" → Open "Edit environment variables for your account".
2. Under "User variables" click **New...**
3. Variable name: `JAVA_HOME`
4. Variable value: the path to your JDK installation, for example:
```
C:\Program Files\Eclipse Adoptium\jdk-21.0.9
```
(Adjust if your installer put it in a different folder.)

After adding, restart your terminal or log out/in to apply changes. On macOS/Linux, add to your shell profile (e.g., `~/.bashrc`, `~/.zshrc`):
```bash
export JAVA_HOME="$(/usr/libexec/java_home -v 21)"
export PATH="$JAVA_HOME/bin:$PATH"
```

---

## 3. Gradle

This project includes the Gradle wrapper — no separate Gradle install required.

Check the wrapper:
Windows:
```powershell
.\gradlew --version
```
macOS/Linux:
```bash
./gradlew --version
```
Recommended Gradle version: 8.14 (the wrapper will use a compatible version automatically).

---

## 4. Run the project

Windows:
```powershell
.\gradlew run
```
macOS/Linux:
```bash
./gradlew run
```

---

## Troubleshooting

- If `java --version` shows a different version, ensure `JAVA_HOME` points to JDK 21 and your PATH points to `$JAVA_HOME/bin`.
- On Windows, double-check if you set the user or system environment variable (user-level is usually fine).
- If Gradle wrapper fails to download, check your network/proxy settings.

---

If you'd like, I can:
- Add badges (Java, Gradle) to the top,
- Expand the troubleshooting section with common errors and fixes,
- Add a short project description and usage examples.

```
