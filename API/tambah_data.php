<?php

if ($_SERVER["REQUEST_METHOD"] == "POST") {
		
$nama = $_POST['nama'];
$jenis = $_POST['jenis'];
$keterangan = $_POST['keterangan'];

$query = "INSERT INTO tabel_barang(Nama,Jenis,Keterangan) values ('$nama','$jenis','$keterangan');";

require_once 'server_mysql.php';

if (mysqli_query($con,$query)){
	echo 'Insert Data Berhasil';
} else {
	echo 'Insert Data Gagal';
}

}
?>