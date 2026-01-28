-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Waktu pembuatan: 27 Jan 2026 pada 10.01
-- Versi server: 8.0.40
-- Versi PHP: 8.3.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_tugasakhir`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `audit_log`
--

CREATE TABLE `audit_log` (
  `id_log` int NOT NULL,
  `aktivitas` varchar(255) NOT NULL,
  `waktu` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `user` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `bobot`
--

CREATE TABLE `bobot` (
  `id_bobot` int NOT NULL,
  `id_kriteria` int NOT NULL,
  `nilai_bobot` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data untuk tabel `bobot`
--

INSERT INTO `bobot` (`id_bobot`, `id_kriteria`, `nilai_bobot`) VALUES
(6, 1, 0.30),
(7, 2, 0.20),
(9, 4, 0.15),
(10, 5, 0.10),
(20, 3, 0.25);

-- --------------------------------------------------------

--
-- Struktur dari tabel `detail_penilaian`
--

CREATE TABLE `detail_penilaian` (
  `id_detail` int NOT NULL,
  `id_penilaian` int NOT NULL,
  `id_kriteria` int NOT NULL,
  `nilai` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data untuk tabel `detail_penilaian`
--

INSERT INTO `detail_penilaian` (`id_detail`, `id_penilaian`, `id_kriteria`, `nilai`) VALUES
(452, 96, 1, 80.00),
(453, 96, 2, 80.00),
(454, 96, 3, 80.00),
(455, 96, 4, 70.00),
(456, 96, 5, 2.00),
(467, 99, 1, 60.00),
(468, 99, 2, 60.00),
(469, 99, 3, 60.00),
(470, 99, 4, 60.00),
(471, 99, 5, 0.00),
(472, 100, 1, 60.00),
(473, 100, 2, 60.00),
(474, 100, 3, 60.00),
(475, 100, 4, 60.00),
(476, 100, 5, 0.00),
(477, 101, 1, 40.00),
(478, 101, 2, 40.00),
(479, 101, 3, 40.00),
(480, 101, 4, 40.00),
(481, 101, 5, 0.00),
(482, 102, 1, 30.00),
(483, 102, 2, 0.00),
(484, 102, 3, 0.00),
(485, 102, 4, 0.00),
(486, 102, 5, 0.00);

-- --------------------------------------------------------

--
-- Struktur dari tabel `hrd`
--

CREATE TABLE `hrd` (
  `id_hrd` int NOT NULL,
  `nama` varchar(100) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data untuk tabel `hrd`
--

INSERT INTO `hrd` (`id_hrd`, `nama`, `username`, `password`) VALUES
(1, 'hrd', 'hrd', '123');

-- --------------------------------------------------------

--
-- Struktur dari tabel `karyawan`
--

CREATE TABLE `karyawan` (
  `id_karyawan` int NOT NULL,
  `nama` varchar(100) NOT NULL,
  `jabatan` varchar(100) DEFAULT NULL,
  `departemen` varchar(100) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data untuk tabel `karyawan`
--

INSERT INTO `karyawan` (`id_karyawan`, `nama`, `jabatan`, `departemen`, `status`) VALUES
(6, 'Asep', 'Programmer', 'tes1', 'Aktif'),
(7, 'Surya', 'Data Scientist', 'tes2', 'Aktif'),
(8, 'Steven', 'Data Analyst', 'tes3', 'Aktif'),
(9, 'Hendri', 'Designer', 'tes4', 'aktif'),
(10, 'Tata', '', '', '');

-- --------------------------------------------------------

--
-- Struktur dari tabel `kriteria`
--

CREATE TABLE `kriteria` (
  `id_kriteria` int NOT NULL,
  `nama_kriteria` varchar(100) NOT NULL,
  `tipe` enum('benefit','cost') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data untuk tabel `kriteria`
--

INSERT INTO `kriteria` (`id_kriteria`, `nama_kriteria`, `tipe`) VALUES
(1, 'Kehadiran', 'benefit'),
(2, 'Disiplin', 'benefit'),
(3, 'Produktivitas', 'benefit'),
(4, 'Kerjasama', 'benefit'),
(5, 'Jumlah Alpha', 'cost');

-- --------------------------------------------------------

--
-- Struktur dari tabel `laporan`
--

CREATE TABLE `laporan` (
  `id_laporan` int NOT NULL,
  `id_saw` int NOT NULL,
  `tanggal_laporan` date NOT NULL,
  `jenis_laporan` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `metode_saw`
--

CREATE TABLE `metode_saw` (
  `id_saw` int NOT NULL,
  `id_penilaian` int NOT NULL,
  `nilai_preferensi` decimal(10,4) NOT NULL,
  `peringkat` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Struktur dari tabel `penilaian`
--

CREATE TABLE `penilaian` (
  `id_penilaian` int NOT NULL,
  `id_karyawan` int NOT NULL,
  `tanggal_penilaian` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data untuk tabel `penilaian`
--

INSERT INTO `penilaian` (`id_penilaian`, `id_karyawan`, `tanggal_penilaian`) VALUES
(96, 7, '2025-12-20'),
(99, 10, '2025-12-20'),
(100, 9, '2025-12-20'),
(101, 8, '2025-12-20'),
(102, 6, '2025-12-20');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `audit_log`
--
ALTER TABLE `audit_log`
  ADD PRIMARY KEY (`id_log`);

--
-- Indeks untuk tabel `bobot`
--
ALTER TABLE `bobot`
  ADD PRIMARY KEY (`id_bobot`),
  ADD KEY `fk_bobot_kriteria` (`id_kriteria`);

--
-- Indeks untuk tabel `detail_penilaian`
--
ALTER TABLE `detail_penilaian`
  ADD PRIMARY KEY (`id_detail`),
  ADD KEY `fk_detail_penilaian` (`id_penilaian`),
  ADD KEY `fk_detail_kriteria` (`id_kriteria`);

--
-- Indeks untuk tabel `hrd`
--
ALTER TABLE `hrd`
  ADD PRIMARY KEY (`id_hrd`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indeks untuk tabel `karyawan`
--
ALTER TABLE `karyawan`
  ADD PRIMARY KEY (`id_karyawan`);

--
-- Indeks untuk tabel `kriteria`
--
ALTER TABLE `kriteria`
  ADD PRIMARY KEY (`id_kriteria`);

--
-- Indeks untuk tabel `laporan`
--
ALTER TABLE `laporan`
  ADD PRIMARY KEY (`id_laporan`),
  ADD KEY `fk_laporan_saw` (`id_saw`);

--
-- Indeks untuk tabel `metode_saw`
--
ALTER TABLE `metode_saw`
  ADD PRIMARY KEY (`id_saw`),
  ADD KEY `fk_saw_penilaian` (`id_penilaian`);

--
-- Indeks untuk tabel `penilaian`
--
ALTER TABLE `penilaian`
  ADD PRIMARY KEY (`id_penilaian`),
  ADD KEY `fk_penilaian_karyawan` (`id_karyawan`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `audit_log`
--
ALTER TABLE `audit_log`
  MODIFY `id_log` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `bobot`
--
ALTER TABLE `bobot`
  MODIFY `id_bobot` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT untuk tabel `detail_penilaian`
--
ALTER TABLE `detail_penilaian`
  MODIFY `id_detail` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=487;

--
-- AUTO_INCREMENT untuk tabel `hrd`
--
ALTER TABLE `hrd`
  MODIFY `id_hrd` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT untuk tabel `karyawan`
--
ALTER TABLE `karyawan`
  MODIFY `id_karyawan` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT untuk tabel `kriteria`
--
ALTER TABLE `kriteria`
  MODIFY `id_kriteria` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT untuk tabel `laporan`
--
ALTER TABLE `laporan`
  MODIFY `id_laporan` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `metode_saw`
--
ALTER TABLE `metode_saw`
  MODIFY `id_saw` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT untuk tabel `penilaian`
--
ALTER TABLE `penilaian`
  MODIFY `id_penilaian` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=103;

--
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `bobot`
--
ALTER TABLE `bobot`
  ADD CONSTRAINT `fk_bobot_kriteria` FOREIGN KEY (`id_kriteria`) REFERENCES `kriteria` (`id_kriteria`) ON DELETE CASCADE;

--
-- Ketidakleluasaan untuk tabel `detail_penilaian`
--
ALTER TABLE `detail_penilaian`
  ADD CONSTRAINT `fk_detail_kriteria` FOREIGN KEY (`id_kriteria`) REFERENCES `kriteria` (`id_kriteria`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_detail_penilaian` FOREIGN KEY (`id_penilaian`) REFERENCES `penilaian` (`id_penilaian`) ON DELETE CASCADE;

--
-- Ketidakleluasaan untuk tabel `laporan`
--
ALTER TABLE `laporan`
  ADD CONSTRAINT `fk_laporan_saw` FOREIGN KEY (`id_saw`) REFERENCES `metode_saw` (`id_saw`) ON DELETE CASCADE;

--
-- Ketidakleluasaan untuk tabel `metode_saw`
--
ALTER TABLE `metode_saw`
  ADD CONSTRAINT `fk_saw_penilaian` FOREIGN KEY (`id_penilaian`) REFERENCES `penilaian` (`id_penilaian`) ON DELETE CASCADE;

--
-- Ketidakleluasaan untuk tabel `penilaian`
--
ALTER TABLE `penilaian`
  ADD CONSTRAINT `fk_penilaian_karyawan` FOREIGN KEY (`id_karyawan`) REFERENCES `karyawan` (`id_karyawan`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
