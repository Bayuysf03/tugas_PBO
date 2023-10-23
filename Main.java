import java.util.Scanner;

class Pecahan {
    private int pembilang;
    private int penyebut;

    public Pecahan(int pembilang, int penyebut) {
        this.pembilang = pembilang;
        this.penyebut = penyebut;
    }

    public Pecahan tambah(Pecahan other) {
        int newPembilang = (this.pembilang * other.penyebut) + (other.pembilang * this.penyebut);
        int newPenyebut = this.penyebut * other.penyebut;
        return new Pecahan(newPembilang, newPenyebut);
    }

    public Pecahan kurang(Pecahan other) {
        int newPembilang = (this.pembilang * other.penyebut) - (other.pembilang * this.penyebut);
        int newPenyebut = this.penyebut * other.penyebut;
        return new Pecahan(newPembilang, newPenyebut);
    }

    public Pecahan kali(Pecahan other) {
        int newPembilang = this.pembilang * other.pembilang;
        int newPenyebut = this.penyebut * other.penyebut;
        return new Pecahan(newPembilang, newPenyebut);
    }

    public Pecahan bagi(Pecahan other) {
        int newPembilang = this.pembilang * other.penyebut;
        int newPenyebut = this.penyebut * other.pembilang;
        return new Pecahan(newPembilang, newPenyebut);
    }

    public String toString() {
        return pembilang + "/" + penyebut;
    }
}

class Matrik {
    private Pecahan[][] data;
    private int baris;
    private int kolom;

    public Matrik(int baris, int kolom) {
        this.baris = baris;
        this.kolom = kolom;
        data = new Pecahan[baris][kolom];
    }

    public void isiMatrik(Scanner input) {
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                System.out.print("Masukkan elemen matriks [" + i + "][" + j + "]: ");
                String pecahanStr = input.next();
                String[] pecahanParts = pecahanStr.split("/");
                int pembilang = Integer.parseInt(pecahanParts[0]);
                int penyebut = Integer.parseInt(pecahanParts[1]);
                data[i][j] = new Pecahan(pembilang, penyebut);
            }
        }
    }

    public Matrik tambah(Matrik other) {
        if (this.baris != other.baris || this.kolom != other.kolom) {
            throw new IllegalArgumentException("Matriks harus memiliki ukuran yang sama untuk penjumlahan.");
        }

        Matrik result = new Matrik(baris, kolom);
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                result.data[i][j] = this.data[i][j].tambah(other.data[i][j]);
            }
        }
        return result;
    }

    public Matrik kurang(Matrik other) {
        if (this.baris != other.baris || this.kolom != other.kolom) {
            throw new IllegalArgumentException("Matriks harus memiliki ukuran yang sama untuk pengurangan.");
        }

        Matrik result = new Matrik(baris, kolom);
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                result.data[i][j] = this.data[i][j].kurang(other.data[i][j]);
            }
        }
        return result;
    }

    public Matrik dot(Matrik other) {
        if (this.kolom != other.baris) {
            throw new IllegalArgumentException("Jumlah kolom matriks pertama harus sama dengan jumlah baris matriks kedua untuk operasi dot.");
        }

        Matrik result = new Matrik(this.baris, other.kolom);
        for (int i = 0; i < this.baris; i++) {
            for (int j = 0; j < other.kolom; j++) {
                Pecahan sum = new Pecahan(0, 1);
                for (int k = 0; k < this.kolom; k++) {
                    sum = sum.tambah(this.data[i][k].kali(other.data[k][j]));
                }
                result.data[i][j] = sum;
            }
        }
        return result;
    }

    public Matrik transpose() {
        Matrik result = new Matrik(this.kolom, this.baris);
        for (int i = 0; i < this.baris; i++) {
            for (int j = 0; j < this.kolom; j++) {
                result.data[j][i] = this.data[i][j];
            }
        }
        return result;
    }

    public Matrik inverse() {
        // Implementasi invers matriks tidak termasuk dalam cakupan jawaban ini.
        // Anda dapat menggunakan metode lain atau library matematika untuk menghitung invers matriks.
        throw new UnsupportedOperationException("Metode invers matriks belum diimplementasikan.");
    }

    public void tampilMatrik() {
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Input matriks pertama
        System.out.print("Masukkan jumlah baris matriks pertama: ");
        int baris1 = input.nextInt();
        System.out.print("Masukkan jumlah kolom matriks pertama: ");
        int kolom1 = input.nextInt();
        Matrik matriks1 = new Matrik(baris1, kolom1);
        System.out.println("Masukkan elemen-elemen matriks pertama:");
        matriks1.isiMatrik(input);

        // Input matriks kedua
        System.out.print("Masukkan jumlah baris matriks kedua: ");
        int baris2 = input.nextInt();
               System.out.print("Masukkan jumlah kolom matriks kedua: ");
        int kolom2 = input.nextInt();
        Matrik matriks2 = new Matrik(baris2, kolom2);
        System.out.println("Masukkan elemen-elemen matriks kedua:");
        matriks2.isiMatrik(input);

        // Operasi matriks
        System.out.println("Operasi Matriks:");
        System.out.println("1. Penjumlahan Matriks");
        System.out.println("2. Pengurangan Matriks");
        System.out.println("3. Dot Matriks");
        System.out.println("4. Transpose Matriks");
        System.out.println("5. Inverse Matriks (belum diimplementasikan)");
        System.out.print("Pilih operasi (1/2/3/4/5): ");
        int choice = input.nextInt();

        Matrik result = null;
        switch (choice) {
            case 1:
                result = matriks1.tambah(matriks2);
                break;
            case 2:
                result = matriks1.kurang(matriks2);
                break;
            case 3:
                result = matriks1.dot(matriks2);
                break;
            case 4:
                result = matriks1.transpose();
                break;
            case 5:
                System.out.println("Operasi Inverse Matriks belum diimplementasikan.");
                break;
            default:
                System.out.println("Pilihan operasi tidak valid.");
        }

        if (result != null) {
            System.out.println("Hasil operasi:");
            result.tampilMatrik();
        }

        input.close();
    }
}

