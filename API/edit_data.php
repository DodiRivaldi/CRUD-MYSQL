<?php 
if($_SERVER['REQUEST_METHOD']=='POST'){
	
	$id_barang = $_POST['id_barang'];
	$nama_barang = $_POST['nama_barang'];
	$jenis_barang = $_POST['jenis_barang'];
	$keterangan_barang = $_POST['keterangan_barang'];
	
	 require_once('server_mysql.php');
	 
	 $sql = "UPDATE tabel_barang SET Nama = '$nama_barang', Jenis = '$jenis_barang', Keterangan = '$keterangan_barang' WHERE id_barang = $id_barang;";
	
	if(mysqli_query($con,$sql)){
		echo 'Berhasil Update Data';
	}else{
		echo 'Gagal Update Data';
	}
}
?>