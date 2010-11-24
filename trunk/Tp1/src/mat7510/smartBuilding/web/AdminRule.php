<?php
require_once("config.php");
//No olvidar de darle permisos de escritura al archivo xml!!
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
function changeState($idRule){

    if (file_exists(XML_FILE)) {
       $xml = simplexml_load_file(XML_FILE);
        $parser =  new SimpleXMLElement(XML_FILE, null, true);

        foreach( $parser->rule as $rule ) {
        //    echo  "ID: $idRule == ".$rule->ruleID."<br>";
            
            if(strcmp($rule->ruleID,$idRule)==0){
            //    echo ("Enabled: ".$rule['enabled']);
                if(strcmp($rule['enabled'],"yes")==0)
                    $rule['enabled']=no;
                else $rule['enabled']=yes;

          //      echo (" >> ".$rule['enabled']."<br>");
                $dom = new DOMDocument('1.0');
                $dom->preserveWhiteSpace = false;
                $dom->formatOutput = true;
                $dom->loadXML($parser->asXML());
                $dom->saveXML();
                $dom->save(XML_FILE);
                return;
            }
        }

    } else {
            echo('Error abriendo test.xml.');
    }
}
//-----------------------------------------------------------------------------------------------------------------------------
if (isset($_GET['idRule'])){
    $idRule=$_GET["idRule"];
    changeState($idRule);
}

?>