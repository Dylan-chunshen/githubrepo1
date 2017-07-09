' Replace encodeURI, encodeURIComponent By GBK Encoding
Function encodeURIComponentGBK(str)
	Dim i, special, tmp, ch, hexch
	special = " `~!@#$%^*()_+-=[]{}:"";'\|<>?,./1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
	tmp = ""
	For i = 1 To Len(str)
		ch = Mid(str, i, 1)
		If (ch = vbCr Or ch = vbLf) Then
            		tmp = tmp & ch
        	Else
            		If InStr(1, special, ch, 1) Then
                		tmp = tmp & ch
            		Else
                		hexch = Hex(Asc(ch))
                		tmp = tmp & "%" & Mid(hexch, 1, 2)
                		If Len(hexch) > 2 Then
                    			tmp = tmp & "%" & Mid(hexch, 3, 2)
                		End If
            		End If
        	End If
	Next
	encodeURIComponentGBK = tmp
End Function