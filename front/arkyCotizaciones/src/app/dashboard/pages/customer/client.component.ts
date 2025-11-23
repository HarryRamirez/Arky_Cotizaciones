import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { FormGroup, FormBuilder, ReactiveFormsModule } from '@angular/forms';
import { ClientService } from '../../../services/client.service';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { ClientInterface } from '../../../interfaces/client.interface';

import { Modal } from 'bootstrap';
import { FormsModule } from '@angular/forms';
import { NgxPaginationModule } from 'ngx-pagination';

@Component({
  selector: 'app-client',
  standalone: true,
  imports: [RouterLink, HttpClientModule, CommonModule, ReactiveFormsModule, FormsModule, NgxPaginationModule],
  templateUrl: './client.component.html',
  styleUrl: './client.component.scss'
})
export default class ClientComponent implements OnInit{

  clients: ClientInterface[]=[];
  page: number = 1; 
  itemsPerPage: number = 10; 

  selectedClient?: ClientInterface;

  selectedClientForm: FormGroup; // Usaremos un formulario reactivo

  clientToDelete: number | null = null;

  searchTerm: string = ''; // Propiedad para enlazar el término de búsqueda


  loading: boolean = false;
  loadingReport: boolean = false;


  constructor(private clientService: ClientService, private formBuilder: FormBuilder) {}

  ngOnInit(): void {
    this.getClients();
    this.initializeForm();
    
  }


  // Metodo para listar los clientes
  getClients(){
    this.loading = true; 
    this.clientService.getAllClients().subscribe({
      next: (result) => {
        this.clients = result;
        this.loading = false; 
      },
      error: (err) => {
        console.log(err);
      }
    })
  }




  // Metodo para obtener un cliente
  getClientById(id: number): void {
    console.log('ID del cliente seleccionado:', id);
    this.clientService.getClientById(id).subscribe({
      next: (data) => {
        this.selectedClient = data;
        console.log('Cliente obtenido:', this.selectedClient); // Verifica el cliente obtenido
      },
      error: (error) => {
        console.error('Error al obtener el cliente', error);
      }
    });
  }



  // Inicializar el formulario sin validaciones
  initializeForm() {
    this.selectedClientForm = this.formBuilder.group({
      name: [''],
      rut: [''],
      rubro: [''],
      email: [''],
      phone: [''],
      address: ['']
    });
  }




  // Método para actualizar el cliente
updateClient(): void {
  if (this.selectedClientForm.valid && this.selectedClient) {
    // Tomamos los valores del formulario reactivo y los asignamos al cliente seleccionado
    const updatedClient = { 
      ...this.selectedClient, 
      ...this.selectedClientForm.value 
    };
    
    this.clientService.updateClient(updatedClient.customerId, updatedClient).subscribe({
      next: (data) => {
        console.log('Cliente actualizado con éxito', data);
        this.getClients(); // Recargar la lista de clientes
        this.selectedClient = null; // Limpiar el cliente seleccionado
        this.selectedClientForm.reset(); // Limpiar el formulario
      },
      error: (error) => {
        console.error('Error al actualizar el cliente', error);
      }
    });
  }
}



  // Cargar los datos del cliente seleccionado en el formulario reactivo
  editClient(client: ClientInterface): void {
    this.selectedClient = client; // Asignamos el cliente seleccionado
    this.selectedClientForm.patchValue({
      name: client.name,
      rut: client.rut,
      rubro: client.rubro,
      email: client.email,
      phone: client.phone,
      address: client.address
    });
  }




  // Método trackBy para mejorar la eficiencia al listar los clientes
  trackClient(index: number, client: ClientInterface): number {
    return client.customerId;
  }



// Metodo del modal de boostrap 
  openDeleteModal(id: number): void {
    this.clientToDelete = id;
  }


  

// Metodo para eliminar cliente
  deleteClient(): void {
    if (this.clientToDelete !== null) {
      this.clientService.deleteClient(this.clientToDelete).subscribe({
        next: () => {
          console.log('Cliente eliminado con éxito');
          this.getClients(); 
          this.clientToDelete = null; // Limpiar el cliente a eliminar después de eliminarlo
        },
        error: (error) => {
          console.error('Error al eliminar el cliente', error);
        }
      });
    }
  }


  // Realizar la búsqueda en el backend
  searchClients(): void {
    if (this.searchTerm.trim() === '') {
      this.getClients(); // Si el campo de búsqueda está vacío, cargar todos los clientes
      return;
    }

    this.loading = true;
    this.clientService.searchCustomersByName(this.searchTerm).subscribe({
      next: (data) => {
        this.clients = data;
        this.loading = false;
      },
      error: (err) => {
        console.error('Error al buscar clientes', err);
        this.loading = false;
      }
    });
  }

  // Manejar el envío del formulario para evitar la recarga de la página
  onSubmit(event: Event): void {
    event.preventDefault(); // Evita la recarga de la página
    this.searchClients(); // Ejecuta la búsqueda cuando se envía el formulario
  }



    // Método  para descargar el reporte Excel
  ExcelReport() {
    this.loadingReport= true;
      this.clientService.ExcelReport().subscribe({
        next: (blob: Blob) => {
          const url = window.URL.createObjectURL(blob);
          const a = document.createElement('a');
          a.href = url;
          a.download = 'clientes.xlsx'; 
          a.click();
          window.URL.revokeObjectURL(url); 
        },
        error: (err) => {
          console.error('Error al descargar el archivo Excel', err);
        },
        complete: () => {
          this.loadingReport= false;
          console.log('Descarga completada');
        }
      });
    }

}
