<?php
require_once("config.php");
$cantRowsForTable=10;
$cantTables=1;
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
function ListDataTable(	$header, $data){
    echo '<div><table>';    
    if($header){
        echo '<thead>';
        echo '<tr>';
        foreach($header as $column)
            echo '<th>'.$column.'</th>';
        echo '</tr>';
        echo '</thead>';
    }    
    
    if($data){
        echo '<tbody>';
        $isPar=false;

        foreach($data as $row){

            if($isPar)
                echo '<tr>';
            else echo('<tr class="odd">');

            $first = true;
            foreach($row as $column){
                if($first){
                    echo '<th align="left">'.$column.'</th>';
                    $first=false;
                }else  echo '<td>'.$column.'</td>';
            }
            
            $isPar=!$isPar;
            echo '</tr>';
        }
        echo '</tbody>';
    }

    echo '</table></div>';
}
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
function getStateOfRules(){
    $states = array();

    if (file_exists(XML_FILE)) {
       $xml = simplexml_load_file('ruleConfig.xml');
        $parser =  new SimpleXMLElement(XML_FILE, null, true);
        $i=0;
        foreach( $parser->rule as $rule ) {
            $aux = array();
            $idRule=$rule->ruleID;
            $aux[0]=$idRule;
            $aux[1]=$rule->ruleDescription;

            if(strcmp($rule['enabled'],"yes")==0)
                $aux[2]=" <a href='#' id='$idRule' onclick='return send(\"".$idRule."\")'>Disabled</a> ";
            else $aux[2]=" <a href='#' id='$idRule' onclick='return send(\"".$idRule."\")'>Enabled</a> ";

            $states[$i]=$aux;
            $i++;
        }
    } else {
        exit('Error abriendo test.xml.');
    }

    return $states;
}
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
?>
<html>
    <head>
        <title>Smart Building</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <link rel="stylesheet" type="text/css" href="css/style.css" media="screen"/>
        <link rel="stylesheet" type="text/css" href="css/styleTable.css" media="screen"/>
    </head>
    <body>

       <script type="text/javascript">

        function send(id){
            $.ajax({
                type: 'GET',
                url: "AdminRule.php",
                data: "idRule="+id,
                success: function() {
                    var boton = document.getElementById(id);
                    if(boton.innerHTML =='<?php echo("Enabled");?>')
                        boton.innerHTML ='<?php echo("Disabled");?>';
                    else boton.innerHTML ='<?php echo("Enabled");?>';
                    return false;
                }
            });
        }        
    </script>


        <div class="header">
            <h1>Smart Building</h1>
        </div>
        <div class="content">

            <div id="paginationdemo" class="demo">
                <h1>Setting Rules:</h1>

                            <?php
                    $header=array("idRule" => "ID Rule","Description" => "Description","Action" => "Action");

                    $rules = getStateOfRules();

                    if(count($rules)>0){
                        $hasta=$cantRowsForTable;

                        if(count($rules)<$cantRowsForTable)
                            $hasta=count($rules);

                        $data=array();
                        for($i=0;$i<$hasta;$i++){
                            $data[$i]=$rules[$i];
                        }

                        $cantTables=1;
                        echo("<div id='p1' class='pagedemo _current' style=''>");
                        ListDataTable($header,$data);
                        echo("</div>");

                        $i=$hasta;
                        $data=array();
                        $count=0;
                        while($i<count($rules)){
                            $data[$count]=$rules[$i];

                            if($count==$cantRowsForTable || $i==count($rules)-1){
                                $count=0;
                                $cantTables++;
                                echo("<div id='p".$cantTables."' class='pagedemo' style='display:none;'>");
                                ListDataTable($header,$data);
                                echo("</div>");
                                $data=array();
                            }
                            $count++;
                            $i++;
                        }
                    }else{
                           echo("<h3>No Rules</h3>");
                    }

                ?>
                    <br clear="all">
                    <br>
                    <?php
             if(count($rules)>0){
             ?>
  			<div id="indice" class='indice'>
                        </div>
                    <?php
             }
           ?>
            </div>
        </div>
          
   		<script type="text/javascript" src="JQuery/jquery-1.3.2.js"></script>
		<script src="JQuery/jquery.paginate.js" type="text/javascript"></script>
		<script type="text/javascript">
		$(function() {			
			$("#indice").paginate({
				count 	: <?php echo($cantTables);?>,
				start 		: 1,
				display   : <?php echo($cantRowsForTable);?>,
				border					: true,
                                border_color			: '#a7c7dc',
				text_color  			: '#1a0e53',
				background_color    	: '#e0e5f1',
				border_hover_color		: '#6986c8',
				text_hover_color  		: 'black',
				background_hover_color	: '#bfc6d7',
				images					: false,
				mouse					: 'press',
				onChange     			: function(page){
					$('._current','#paginationdemo').removeClass('_current').hide();
                                            $('#p'+page).addClass('_current').show();
					}
			});
		});
		</script>
	</body>
</html>
