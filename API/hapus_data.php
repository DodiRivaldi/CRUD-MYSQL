<?php
	$id_barang = $_POST['id_barang'];
	
	require_once('server_mysql.php');
	 
	$sql = "DELETE FROM tabel_barang WHERE id_barang=$id_barang;";
	
	if(mysqli_query($con,$sql)){
		echo 'Berhasil Menghapus Data';
	}else{
		echo 'Gagal Menghapus Data';
	}

?>