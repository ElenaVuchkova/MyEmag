<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>jQuery ComboBox</title>
    <link rel="stylesheet" href="../../jqwidgets/styles/jqx.base.css" type="text/css" />
    <script type="text/javascript" src="../../scripts/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="../../jqwidgets/jqxcore.js"></script>
    <script type="text/javascript" src="../../jqwidgets/jqxbuttons.js"></script>
    <script type="text/javascript" src="../../jqwidgets/jqxscrollbar.js"></script>
    <script type="text/javascript" src="../../jqwidgets/jqxlistbox.js"></script>
    <script type="text/javascript" src="../../jqwidgets/jqxcombobox.js"></script>
</head>
<body>
    <div id='content'>
        <script type="text/javascript">
            $(document).ready(function () {
                var source = [
                    "Affogato",
                    "Americano",
                    "Bicerin",
                    "Breve",
                    "Café Bombón",
                    "Café au lait",
                    "Caffé Corretto",
                    "Café Crema",
                    "Caffé Latte",
		        ];
                // Create a jqxComboBox
                $("#jqxcombobox").jqxComboBox({ source: source, selectedIndex: 0, width: '200px', height: '25px' });
                // disable the sixth item.
                $("#jqxcombobox").jqxComboBox('disableAt', 5);
                // bind to 'select' event.
                $('#jqxcombobox').bind('select', function (event) {
                    var args = event.args;
                    var item = $('#jqxcombobox').jqxComboBox('getItem', args.index);
                    alert('Selected: ' + item.label);
                });
            });
        </script>
        <div id='jqxcombobox'>
        </div>
    </div>
</body>
</html>