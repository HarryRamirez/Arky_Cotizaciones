import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { ClientInterface } from '../../../interfaces/client.interface';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { ClientService } from '../../../services/client.service';
import { FormsModule } from '@angular/forms';
import { Modal } from 'bootstrap';


@Component({
  selector: 'app-add-client',
  standalone: true,
  imports: [RouterLink, HttpClientModule, CommonModule, FormsModule],
  templateUrl: './add-client.component.html',
  styleUrl: './add-client.component.scss'
})
export default class AddClientComponent implements OnInit{
  
  clients: ClientInterface[]=[];

  selectedClient: ClientInterface = {
    customerId: null,
    name: '',
    email: '',
    phone: '',
    address: '',
    rut: '',
    rubro: '',
  };

  constructor(private clientService: ClientService, private router: Router) {}

  ngOnInit(): void {
    this.getClients();
  }



  // Metodo para listar clientes
  getClients(){
    this.clientService.getAllClients().subscribe({
      next: (result) => {
        this.clients = result;
      },
      error: (err) => {
        console.log(err);
      }
    })
  }



// Metodo para guardar cliente
saveClient(): void {
  if (this.selectedClient) {
    this.clientService.saveClient(this.selectedClient).subscribe({
      next: (data) => {
        console.log('Cliente guardado con éxito', data);
        this.getClients;


          // Mostrar el modal de éxito
          const modalElement = document.getElementById('staticBackdrop');
          const modal = new Modal(modalElement!);
          modal.show();

          // Ocultar el modal después de 3 segundos
          setTimeout(() => {
            modal.hide();
          }, 2000);
        // Limpiar el formulario asignando un nuevo objeto vacío
        this.selectedClient = {
          customerId: null,
          name: '',
          email: '',
          phone: '',
          address: '',
          rut: '',
          rubro: ''
        };
      },
      error: (error) => {
        console.error('Error al guardar el cliente', error);
      }
    });
  }
}



navigateAndRefresh() {
  this.router.navigate(['/dashboard/client']).then(() => {
    window.location.reload();  // Refresca la página
  });
}


}
