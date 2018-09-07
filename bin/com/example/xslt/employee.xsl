<?xml version="1.0" encoding="ISO-8859-1"?>
<html xsl:version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns="http://www.w3.org/1999/xhtml">
	<body>
		<center>
			<table border="1" bordercolor="red" width="50%">
				<xsl:for-each select="employees/employee">
					<tr>
						<td>
							<xsl:value-of select="name" />
						</td>
						<td>
							<xsl:value-of select="salary" />
						</td>
						<td>
							<xsl:value-of select="designation" />
						</td>
					</tr>
				</xsl:for-each>
			</table>
		</center>
	</body>
</html>
