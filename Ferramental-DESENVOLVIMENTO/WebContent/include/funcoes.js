/**
 * Coleção de funções simples em javascript
 */

/*
* função para permitir a digitação de números decimais e inteiros
* MODOS DE USAR:
* 
* Para permitir a digitação de números inteiros de decimais positivos ou negativos
* onkeydown="ForceNumericInput(event, this, true, true)"
* 
* Para permitir a digitação de inteiros positivos ou negativos
* onkeydown="ForceNumericInput(event, this, false, true)" 
* 
* Para permitir a digitação de inteiros e decimais positivos
* onkeydown="ForceNumericInput(event, this, true, false)" 
* 
* Para permitir a digitação de somente inteiros positivos
* onkeydown="ForceNumericInput(event, this, false, false)" 
* 
*/
function ForceNumericInput(event, This, AllowDecimal, AllowMinus)
{
	if(arguments.length == 1)
	{
		var s = This.value;
		// garante que o sinal de "-" seja o primeiro do índice
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
	if(event.keyCode == 189)     // sinal de número de negativo
	{
		if(AllowMinus == false)
		{
			CancelEventExecution(event);
			return;
		}
		// aguarda até que o controle tenha sido atualizado
		var s = "ForceNumericInput(document.getElementById('"+This.id+"'))";
		setTimeout(s, 250);
		return;
	}
	
	if(AllowDecimal && event.keyCode == 188)
	{
		if(This.value.indexOf(",") >= 0)
		{
			// restringe a digitação de apenas uma vírgula
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
* Cancela a execução de uma function mapeada por um evento
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
  