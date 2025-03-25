<%-- 
    Document   : index
    Created on : 24-Mar-2025, 2:10:09 pm
    Author     : hp
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Currency Converter</title>
    <script>
        function convertCurrency() {
            var amount = document.getElementById("amount").value;
            var from = document.getElementById("from").value;
            var to = document.getElementById("to").value;

            var xhr = new XMLHttpRequest();
            xhr.open("GET", "api/currency/convert?amount=" + amount + "&from=" + from + "&to=" + to, true);
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    var result = JSON.parse(xhr.responseText);
                    document.getElementById("result").innerHTML =
                        "Original Amount: " + result.originalAmount + " " + result.fromCurrency + "<br>" +
                        "Converted Amount: " + result.convertedAmount + " " + result.toCurrency;
                } else if (xhr.readyState === 4) {
                    document.getElementById("result").innerHTML = "Error: " + xhr.responseText;
                }
            };
            xhr.send();
        }
    </script>
</head>
<body>
    <h1>Currency Converter</h1>
    <form>
        Amount: <input type="text" id="amount" name="amount"><br>
        From: <select id="from" name="from">
            <option value="USD">USD</option>
            <option value="EUR">EUR</option>
        </select><br>
        To: <select id="to" name="to">
            <option value="USD">USD</option>
            <option value="EUR">EUR</option>
        </select><br>
        <input type="button" value="Convert" onclick="convertCurrency()">
    </form>
    <div id="result"></div>
</body>
</html>
