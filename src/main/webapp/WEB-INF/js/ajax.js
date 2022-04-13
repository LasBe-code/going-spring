function ajax(url, params,callback, method) {
	const xhttp = new XMLHttpRequest()
	
	method = method.toUpperCase()
	if(method != 'POST'){method='GET'}
	if(method == 'GET'){url = url+"?"+params}
	
	xhttp.open(method, url, true)
	xhttp.setRequestHeader('Content-Type', 'application/x-www-form-unlencoded')
	xhttp.send(method == 'POST' ? params : null)
	xhttp.onreadystatechange = callback
}