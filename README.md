# 🎌 AnimeExplorer - Aplikasi Eksplorasi Anime Android

Aplikasi mobile Android sederhana yang dinamis untuk mengeksplorasi katalog anime menggunakan **Kotlin**, **Jetpack Compose**, **Material Design 3**, **Navigation Compose**, dan **MVVM Architecture**, yang terintegrasi langsung dengan **Tenrai REST API**.

---

## 📱 Screenshot Aplikasi

| Home Screen (List Anime) | Detail Screen (Informasi & Sinopsis) |
| :---: | :---: |
| <img width="238" src="https://github.com/user-attachments/assets/2b52ac18-6a40-47aa-b89e-fe83c84bab7c" /> | <img width="241" src="https://github.com/user-attachments/assets/09b759d8-aae0-4849-92c0-93fcfc83db53" /> |

---

## 🚀 Fitur Utama

- **Daftar Anime (Home Screen):** Menampilkan daftar anime secara dinamis menggunakan `LazyColumn` yang berisi Judul, Skor Rating (⭐), Tahun Rilis (📅), dan Jumlah Episode (📺).
- **Detail Anime (Detail Screen):** Menampilkan informasi detail anime yang dipilih secara lengkap, termasuk Rating Umur dan Sinopsis.
- **Handling UI State Dinamis:** Mengelola 3 kondisi UI secara efisien:
  - ⏳ **Loading:** Menampilkan `CircularProgressIndicator` saat memuat data dari API.
  - ❌ **Error:** Menampilkan pesan kesalahan beserta tombol **"Coba Lagi"** (Retry) ketika koneksi terputus.
  - ✅ **Success:** Merender daftar anime atau detail anime setelah data berhasil didapatkan.
- **Navigation Compose:** Perpindahan halaman yang mulus antara Home Screen dan Detail Screen.

---

## 🛠️ Teknologi & Library yang Digunakan

- **Language:** Kotlin
- **UI Toolkit:** Jetpack Compose + Material Design 3
- **Architecture:** MVVM (Model - View - ViewModel) + Repository Pattern
- **Networking:** Retrofit 2 (`2.11.0`) + Gson Converter (`2.11.0`)
- **API Source:** Tenrai API (`https://api.tenrai.org/v1/`)
- **Navigation:** Navigation Compose (`2.7.7`)
- **State Management:** Kotlin Coroutines & `StateFlow` (`lifecycle-viewmodel-compose:2.8.7`)

---

## 🏗️ Struktur Proyek & Arsitektur (MVVM)

Proyek ini menerapkan pola arsitektur **MVVM (Model-View-ViewModel)** dengan struktur package sebagai berikut:

```text
com.example.animeexplorer
 ├── data
 │    ├── model
 │    │    └── Anime.kt               # Data class Anime, AnimeResponse, AnimeDetailResponse
 │    ├── remote
 │    │    ├── AnimeApiService.kt    # Endpoint Retrofit (getAnimeList & getAnimeDetail)
 │    │    └── RetrofitClient.kt     # Singleton Retrofit Client (Base URL: https://api.tenrai.org/v1/)
 │    └── repository
 │         └── AnimeRepository.kt    # Repository penarik data dari API
 ├── ui
 │    ├── screens
 │    │    ├── HomeScreen.kt          # Tampilan daftar anime dengan LazyColumn
 │    │    └── DetailScreen.kt        # Tampilan detail anime lengkap
 │    ├── theme
 │    │    ├── Color.kt               # Skema Warna M3
 │    │    ├── Theme.kt               # Custom Theme AnimeExplorerTheme
 │    │    └── Type.kt                # Custom Typography
 │    ├── viewmodel
 │    │    ├── AnimeUiState.kt        # Sealed interface untuk Handling Loading, Success, Error
 │    │    └── AnimeViewModel.kt      # ViewModel pengelola state UI via StateFlow
 │    └── AnimeApp.kt                 # NavHost Manager (Route: "home" & "detail/{animeId}")
 └── MainActivity.kt                  # Entry point utama aplikasi
```

---

## ⚙️ Penjelasan Teknis Alur Kode

1. **Data Layer (`data/`):**
   - `Anime.kt`: Mendefinisikan model data JSON dari Tenrai API dengan pemetaan `@SerializedName` yang aman dan mendukung null safety.
   - `AnimeApiService.kt` & `RetrofitClient.kt`: Mengonfigurasi Retrofit untuk memanggil endpoint `/anime` dan `/anime/{id}` dari Base URL `https://api.tenrai.org/v1/`.
   - `AnimeRepository.kt`: Menjadi jembatan abstraksi antara data source (Retrofit) dan domain layer/ViewModel.

2. **ViewModel Layer (`ui/viewmodel/`):**
   - `AnimeUiState.kt`: Menggunakan `sealed interface` (`Loading`, `Success`, `Error`) untuk merepresentasikan status antarmuka pengguna secara *type-safe*.
   - `AnimeViewModel.kt`: Menggunakan Coroutine `viewModelScope` untuk memanggil repository secara asynchronous dan memperbarui `StateFlow` (`homeUiState` & `detailUiState`).

3. **UI Layer (`ui/screens/` & `ui/`):**
   - `HomeScreen.kt`: Mengumpulkan (`collectAsState`) `homeUiState` dari ViewModel. Jika state `Success`, data ditampilkan dalam `LazyColumn` berisi `AnimeItemCard`. Jika `Error`, menampilkan pesan beserta tombol Retry.
   - `DetailScreen.kt`: Menggunakan `LaunchedEffect(animeId)` untuk memicu pemicuan `getAnimeDetail(animeId)` ketika layar dibuka.
   - `AnimeApp.kt`: Mengatur `NavHost` dengan dua rute (`"home"` dan `"detail/{animeId}"`) untuk navigasi antar layar.
   - `MainActivity.kt`: Mengatur konten utama menggunakan `setContent` dalam balutan `AnimeExplorerTheme`.

---

## 💻 Cara Menjalankan Proyek

1. **Clone Repository ini:**
   ```bash
   git clone https://github.com/USERNAME/AnimeExplorer.git
   ```
2. Buka proyek di **Android Studio** (disarankan versi terbaru/Android Studio Ladybug or newer).
3. Pastikan laptop/PC terhubung ke internet.
4. Pilih **Emulator Android** atau **HP Fisik** (USB Debugging Aktif).
5. Klik **Run `▶`** (atau tekan `Shift + F10`).

---

## 📝 Lisensi & Penulis

Dibuat untuk memenuhi Tugas Submission Aplikasi Mobile Eksplorasi Anime.
