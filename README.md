# English Words 📖

An Android dictionary app built with Jetpack Compose. Search for English words, view their definitions, pronunciation, and examples, save your favorite words for later, and keep track of your search history — all stored locally on your device.

## ✨ Features

- **Word search** — look up any English word and get its definition, pronunciation, and example usage via the [Free Dictionary API](https://freedictionaryapi.com/).
- **Save words** — bookmark words you want to remember, stored locally in a Room database.
- **Search history** — automatically keeps track of your last searches (capped at the most recent entries).
- **Yes/No predictions** — a fun extra screen that fetches a random yes/no answer (with a GIF!) from [yesno.wtf](https://yesno.wtf/).
- **Dark & light theme support** — fully adapts to the system theme using Material 3.
- **Robust error handling** — distinguishes between network issues and other failures, showing clear, user-friendly messages instead of crashing.

## 📱 Screenshots

<img width="360" height="800" alt="Screenshot_2026-10-02-13-36-30-090_com example englishwords" src="https://github.com/user-attachments/assets/0cd7d316-4a54-4efd-8823-2ac019b467e8" />
<img width="360" height="800" alt="Screenshot_2026-10-02-13-36-39-262_com example englishwords" src="https://github.com/user-attachments/assets/46db02ee-e339-4c96-9a37-0d38a5a36eef" />
<img width="360" height="800" alt="Screenshot_2026-10-02-13-36-35-211_com example englishwords" src="https://github.com/user-attachments/assets/44c47325-8813-475d-ba47-a1e72f81924b" />


## 🛠️ Tech Stack

- **Kotlin** — primary language
- **Jetpack Compose** — declarative UI toolkit
- **Material 3** — theming and UI components
- **Navigation Compose** — screen navigation
- **ViewModel + StateFlow** — state management following an MVVM-style architecture
- **Room** — local persistence for saved words and search history
- **Retrofit + kotlinx.serialization** — networking and JSON parsing, consuming two independent REST APIs
- **Kotlin Coroutines** — asynchronous operations

## 🏗️ Architecture

The app follows an MVVM-inspired structure:

- **UI (Compose)** — observes state exposed by the ViewModel via `StateFlow` and renders accordingly (`Initial`, `Loading`, `Success`, `Error`).
- **ViewModel** — holds UI state, survives configuration changes, and coordinates calls to the network and local database.
- **Data layer** — Room (`WordDao`, `HistoryDao`) for persistence, and Retrofit services for remote data.

A single shared `ViewModel` instance is created at the activity level and passed down to each screen, ensuring consistent state (e.g. saved words, search history) across the whole navigation graph.

## 🚀 Getting Started

1. Clone the repository
2. Open the project in Android Studio
3. Sync Gradle and run on an emulator or physical device (minSdk 24)

## 📚 What I Learned

This project was built as a hands-on way to learn Android development fundamentals, including:
- Structuring network calls and handling different failure modes gracefully
- Designing a local database schema and understanding Room's `Flow`-based reactive queries
- Managing ViewModel lifecycle and scoping across a navigation graph
- Building a cohesive UI with Jetpack Compose and Material 3 theming

## 📄 License

This project is for educational purposes.
