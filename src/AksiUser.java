import java.util.Map;
import java.util.Scanner;

public class AksiUser extends Aksi {
    @Override
    public void tampilanAksi() {
        System.out.println("Aksi User:");
        System.out.println("1. Pesan Film");
        System.out.println("2. Lihat List Film");
        System.out.println("3. Lihat Pesanan");
        System.out.println("4. Logout");
        System.out.println("5. Tutup Aplikasi");
    }

    @Override
    public void keluar() {
        Akun.logout();
        System.out.println("Anda telah logout.");
    }

    @Override
    public void tutupAplikasi() {
        System.out.println("Aplikasi ditutup.");
        System.exit(0);
    }

    @Override
    public void lihatListFilm() {
        //tambahan kode yang diperlukan
        System.out.println("Daftar Film:");
        for (Film film : Film.getFilms().values()) {
            System.out.println(film.getName() + " - " + film.getDescription() + " - Harga: " + film.getPrice() + " - Stok: " + film.getStock());
        }
}

    public void lihatSaldo() {
        //tambahan kode yang diperlukan
        User currentUser = Akun.getCurrentUser();
            System.out.println("Saldo anda: " + currentUser.getSaldo());
    }

    public void pesanFilm() {
        //tambahan kode yang diperlukan
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nama Film yang ingin dipesan: ");
        String namaFilm = scanner.nextLine();
        Film film = Film.getFilms().get(namaFilm);
    
        if (film == null) {
            System.out.println("Film yang dicari tidak ditemukan.");
            return;
        }
    
        System.out.print("Jumlah tiket yang ingin dipesan: ");
        int jumlahTiket = scanner.nextInt();
    
        if (jumlahTiket > film.getStock()) {
            System.out.println("Stok tiket tidak mencukupi.");
            return;
        }
    
        double totalHarga = film.getPrice() * jumlahTiket;
        User currentUser = Akun.getCurrentUser();
    
        if (totalHarga > currentUser.getSaldo()) {
            System.out.println("Saldo tidak mencukupi, saldo yang dimiliki " + currentUser.getSaldo() + ".");
            return;
        }
    
        currentUser.setSaldo(currentUser.getSaldo() - totalHarga);
        film.setStock(film.getStock() - jumlahTiket);
        currentUser.addPesanan(film, jumlahTiket);
        System.out.println("Tiket berhasil dipesan.");
    }

    public void lihatPesanan() {
        //tambahan kode yang diperlukan
        User currentUser = Akun.getCurrentUser();
        Map<String, Pesanan> pesanan = currentUser.getPesanan();
    
        if (pesanan.isEmpty()) {
            System.out.println("Kamu belum pernah melakukan pemesanan.");
            return;
        }
    
        for (Pesanan p : pesanan.values()) {
            System.out.println("Film: " + p.getFilm().getName() + " - Jumlah: " + p.getKuantitas() + " - Total Harga: " + (p.getFilm().getPrice() * p.getKuantitas()));
        }
    }

}
