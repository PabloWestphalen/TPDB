$(document).ready(function(){
     /* #imagem � o id do input, ao alterar o conteudo do input execurar� a fun��o baixo */
     $('#cover').live('change',function(){
         $('#visualizar').html('<img src="ajax-loader.gif" alt="Enviando..."/> Enviando...');
        /* Efetua o Upload sem dar refresh na pagina */
         $('#formulario').ajaxForm({
            target:'#visualizar' // o callback ser� no elemento com o id #visualizar
         }).submit();
     });
 });
