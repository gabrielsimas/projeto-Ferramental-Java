/**
 * Cole��o de fun��es simples em javascript
 */

/*
* fun��o para permitir a digita��o de n�meros decimais e inteiros
* MODOS DE USAR:
* 
* Para permitir a digita��o de n�meros inteiros de decimais positivos ou negativos
* onkeydown="ForceNumericInput(event, this, true, true)"
* 
* Para permitir a digita��o de inteiros positivos ou negativos
* onkeydown="ForceNumericInput(event, this, false, true)" 
* 
* Para permitir a digita��o de inteiros e decimais positivos
* onkeydown="ForceNumericInput(event, this, true, false)" 
* 
* Para permitir a digita��o de somente inteiros positivos
* onkeydown="ForceNumericInput(event, this, false, false)" 
* 
*/
function ForceNumericInput(event, This, AllowDecimal, AllowMinus)
{
	if(arguments.length == 1)
	{
		var s = This.value;
		// garante que o sinal de "-" seja o primeiro do �ndice
		var i = s.lastIndexOf("-");
		if(i == -1)
		return;
		if(i != 0)
		This.value = s.substring(0,i)+s.substring(i+1);
		return;
	}
	switch(event.keyCode)
	{
		case 8:     // backspace
		case 9:     // tab
		case 37:    // left arrow
		case 39:    // right arrow
		case 46:    // delete
		event.returnValue = true;
		return;
	}
	if(event.keyCode == 189)     // sinal de n�mero de negativo
	{
		if(AllowMinus == false)
		{
			CancelEventExecution(event);
			return;
		}
		// aguarda at� que o controle tenha sido atualizado
		var s = "ForceNumericInput(document.getElementById('"+This.id+"'))";
		setTimeout(s, 250);
		return;
	}
	
	if(AllowDecimal && event.keyCode == 188)
	{
		if(This.value.indexOf(",") >= 0)
		{
			// restringe a digita��o de apenas uma v�rgula
			CancelEventExecution(event);
			return;
		}
		event.returnValue = true;
		return;
	}
	// permite caracteres entre 0 e 9
	if(event.keyCode>= 48 && event.keyCode <= 57)
		{
			event.returnValue = true;
			return;
		}
	CancelEventExecution(event);
	}

/*
* Cancela a execu��o de uma function mapeada por um evento
*/
function CancelEventExecution(event)
{
	if (navigator.appName == "Netscape")
    {
		event.preventDefault();
    }
    else
    {
    	event.returnValue = false;
    }
    
}
  