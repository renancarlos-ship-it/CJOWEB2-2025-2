var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
var tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
  return new bootstrap.Tooltip(tooltipTriggerEl)
})

var myModal = document.getElementById('deleteModal'); 

if (myModal) {
	var bsModal = new bootstrap.Modal(myModal);
	
	myModal.addEventListener('show.bs.modal', function (event) {
	  var button = event.relatedTarget;
	  
	  var id = button.getAttribute('data-bs-id');
	  var type = button.getAttribute('data-bs-type'); 
	
	  var modalTitle = myModal.querySelector('.modal-title');
	  var modalBody = myModal.querySelector('.modal-body p'); 
	  var modalButton = myModal.querySelector('.modal-footer #confirmDeleteBtn');
	
	  if (type === 'car') {
	  	modalTitle.textContent = 'Excluir Veículo #' + id;
        if(modalBody) modalBody.textContent = 'Tem certeza que deseja excluir o veículo?';
	  } else {
	  	modalTitle.textContent = 'Excluir Item #' + id;
	  }
	  
	  var newButton = modalButton.cloneNode(true);
	  modalButton.parentNode.replaceChild(newButton, modalButton);
	  
	  newButton.addEventListener('click', function(){
			deleteItem(button, id, type);
			bsModal.hide();
		});
	});
}

function deleteItem(button, id, type) {
	var row = button.closest('tr');
	
	let url = "";
	
	if (type === 'car') {
		url = "carRegisterServlet?action=delete&car-id=" + id;
	} else if (type === 'client') {
		url = "clientRegisterServlet?action=delete&client-id=" + id;
	}

	fetch(url)
		.then(response => {
			return response.json(); 
		})
		.then(data => {
			if (data === true) {
				if (row) {
					row.remove(); 
				} else {
					window.location.reload(); 
				}
			} else {
				alert("Não foi possível excluir. O item pode estar vinculado a uma locação.");
			}
		})
		.catch(error => console.log('Erro de solicitação', error));
}
var myModal = document.getElementById('deleteModal');
if (myModal) {
    var bsModal = new bootstrap.Modal(myModal);
    
    myModal.addEventListener('show.bs.modal', function (event) {
      var button = event.relatedTarget;
      var id = button.getAttribute('data-bs-id');
      var type = button.getAttribute('data-bs-type');
    
      var modalTitle = myModal.querySelector('.modal-title');
      var modalButton = myModal.querySelector('.modal-footer #confirmDeleteBtn');
    
      if (type === 'car') {
        modalTitle.textContent = 'Excluir Veículo #' + id;
      } else if (type === 'rental') {
        modalTitle.textContent = 'Excluir Locação #' + id;
      } else {
        modalTitle.textContent = 'Excluir Item #' + id;
      }
      
      var newButton = modalButton.cloneNode(true);
      modalButton.parentNode.replaceChild(newButton, modalButton);
      
      newButton.addEventListener('click', function(){
            deleteItem(button, id, type);
            bsModal.hide();
        });
    });
}

function deleteItem(button, id, type) {
    var row = button.closest('tr');
    let url = "";
    
    if (type === 'car') {
        url = "carRegisterServlet?action=delete&car-id=" + id;
    } else if (type === 'client') {
        url = "clientRegisterServlet?action=delete&client-id=" + id;
    } else if (type === 'rental') {
        url = "rentalServlet?action=delete&rental-id=" + id;
    }

    fetch(url)
        .then(response => response.json())
        .then(data => {
            if (data === true) {
                if (row) row.remove();
                else window.location.reload();
            } else {
                alert("Erro ao excluir. Tente novamente.");
            }
        })
        .catch(error => console.log('Erro de solicitação', error));
}