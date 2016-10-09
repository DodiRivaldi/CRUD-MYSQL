        <?php

        $id_barang= $_GET["id_barang"];
        
		require_once 'server_mysql.php';
		
        $query = "select * from tabel_barang where id_barang='$id_barang'";
        $hasil = mysqli_query($con, $query)or die("ERROR " . mysqli_error($con));
        $data = array();

        while ($row = mysqli_fetch_assoc($hasil)) {
            $data[] = $row;
        }
        echo json_encode($data);
        mysqli_close($con);
    
        ?>