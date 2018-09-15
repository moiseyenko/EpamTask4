<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
<title>Help Page</title>
</head>
<body>
	<h2>Pleast submit your information</h2>
	<form action="ServletDemo" method="post">
		<table border="1">
			<tr>
				<td valign="top"><label for="parser">Please, choose parsing type: </label>
				<select id="parser" name="parser">
						<option value="sax">SAX</option>
						<option value="dom">DOM</option>
						<option value="stax">StAX</option>
				</select></td>
			</tr>
			<tr><td valign="top">Your file: <input type="file" name="file" size="20" accept=".xml"></td></tr>
			<tr>
				<td valign="top"><input type="submit" value="SubmitInfo"></td>
			</tr>
		</table>
	</form>
</body>
</html>
</body>
</html>