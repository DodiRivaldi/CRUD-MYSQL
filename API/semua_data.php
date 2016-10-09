<?php

require_once 'server_mysql.php';

$query = "select * from tabel_barang";

$sql = mysqli_query($con,$query);

$ray = array();

while ($row = mysqli_fetch_array($sql)){
	array_push($ray, array(
	
	"id_barang"=>$row['id_barang'],
	"Nama"=>$row['Nama'],
	"Jenis"=>$row['Jenis'],
	"Keterangan"=>$row['Keterangan']
	
	));
}

echo json_encode($ray);
mysqli_close($con);

?>