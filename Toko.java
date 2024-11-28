import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

// Superclass User
class User {
    protected String username;
    protected String password;
    protected String captcha;

    // Constructor untuk menginisialisasi username, password, dan captcha
    public User(String username, String password, String captcha) {
        this.username = username;
        this.password = password;
        this.captcha = captcha;
    }

    // Method untuk validasi login dengan membandingkan input dengan nilai yang sudah ditentukan
    public boolean validateLogin() {
        return username.equals("fatih athaya") && password.equals("0612") && captcha.equals("l4n1");
    }
}

// Subclass Kasir yang mewarisi dari kelas User
class Kasir extends User {
    private String namaKasir;

    // Constructor untuk menginisialisasi username, password, captcha, dan nama kasir
    public Kasir(String username, String password, String captcha, String namaKasir) {
        super(username, password, captcha);  // Memanggil constructor superclass
        this.namaKasir = namaKasir;
    }

    // Method untuk mendapatkan nama kasir
    public String getNamaKasir() {
        return namaKasir;
    }
}

// Exception untuk menangani input yang tidak valid
class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);  // Memanggil constructor superclass Exception untuk menyimpan pesan error
    }
}

public class Toko {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            // Input proses login
            System.out.println("+-----------------------------------------------------+");
            System.out.print("Username : ");
            String username = scanner.nextLine();
            System.out.print("Password : ");
            String password = scanner.nextLine();
            System.out.print("Captcha  : ");
            String captcha = scanner.nextLine();

            // Membuat objek Kasir
            Kasir kasir = new Kasir(username, password, captcha, "UDIN");

            // Cek login
            if (!kasir.validateLogin()) {
                System.out.println("Login gagal, silakan diulangi.");
                return;
            }

            // Input transaksi
            System.out.println("+-----------------------------------------------------+");
            System.out.print("No. Faktur      : ");
            String noFaktur = scanner.nextLine();
            System.out.print("Kode Barang     : ");
            String kodeBarang = scanner.nextLine();
            System.out.print("Nama Barang     : ");
            String namaBarang = scanner.nextLine();
            double hargaBarang;
            int jumlahBeli;

            // Validasi harga dan jumlah beli
            System.out.print("Harga Barang    : ");
            hargaBarang = scanner.nextDouble();
            if (hargaBarang <= 0) {
                throw new InvalidInputException("Harga barang tidak valid!");  // Lempar exception jika harga tidak valid
            }
            System.out.print("Jumlah Beli     : ");
            jumlahBeli = scanner.nextInt();
            if (jumlahBeli <= 0) {
                throw new InvalidInputException("Jumlah beli tidak valid!");  // Lempar exception jika jumlah beli tidak valid
            }
            scanner.nextLine(); // Clear buffer untuk input nama kasir

            // Menampilkan semua output setelah input selesai
            System.out.println("+-----------------------------------------------------+");
            System.out.println("Selamat Datang di Supermarket JavaMart");
            System.out.println("Tanggal dan Waktu: " + getCurrentDateTime());  // Menampilkan tanggal dan waktu
            System.out.println("+-----------------------------------------------------+");
            System.out.println("No. Faktur      : " + noFaktur);
            System.out.println("Kode Barang     : " + kodeBarang);
            System.out.println("Nama Barang     : " + namaBarang);
            System.out.println("Harga Barang    : " + formatCurrency(hargaBarang));  // Format harga dalam bentuk mata uang
            System.out.println("Jumlah Beli     : " + jumlahBeli);
            double totalHarga = hargaBarang * jumlahBeli;
            System.out.println("TOTAL           : " + formatCurrency(totalHarga));  // Format total harga
            System.out.println("+-----------------------------------------------------+");
            System.out.println("Kasir           : " + kasir.getNamaKasir());  // Menampilkan nama kasir
            System.out.println("+-----------------------------------------------------+");

        } catch (InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());  // Menangani error khusus terkait input yang tidak valid
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());  // Menangani exception lain yang tidak terduga
        }
    }

    // Method untuk mendapatkan tanggal dan waktu saat ini
    private static String getCurrentDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  // Format tanggal dan waktu
        return formatter.format(new Date());  // Mengembalikan tanggal dan waktu dalam format yang ditentukan
    }

    // Method untuk format harga dalam bentuk mata uang
    private static String formatCurrency(double amount) {
        return String.format("Rp %.2f", amount);  // Memformat jumlah uang dalam format mata uang Indonesia
    }
}