import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { ServiceService } from '../../../services/service.service';
import { ServiceInterface } from '../../../interfaces/service.interface';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Category } from '../../../interfaces/category.interface';
import { Service } from '../../../interfaces/tipo.interface';
import { CategoryService } from '../../../services/category.service';
import { NgxPaginationModule } from 'ngx-pagination';

@Component({
  selector: 'app-service',
  standalone: true,
  imports: [RouterLink, HttpClientModule, CommonModule, ReactiveFormsModule, FormsModule, NgxPaginationModule],
  templateUrl: './service.component.html',
  styleUrl: './service.component.scss'
})
export default class ServiceComponent implements OnInit{

  services: ServiceInterface[]=[];
  page: number = 1; 
  itemsPerPage: number = 10; 

  serviceForm!: FormGroup;
  categories: Category[] = [];
  selectedService: Service | null = null;  // Servicio seleccionado para editar

  selectedProduct?: ServiceInterface;
  selectedProductForm: FormGroup; // Usaremos un formulario reactivo

  productToDelete: number | null = null;

  searchTerm: string = ''; // Propiedad para enlazar el término de búsqueda

  loading: boolean = false;

 

  constructor(private serviceService: ServiceService, 
    private formBuilder: FormBuilder, 
    private categoryService: CategoryService) {}

  ngOnInit(): void {
    this.getServices();
    this.initializeForm();

  
    this.serviceForm = this.formBuilder.group({
      productId: [null],
      name: ['', [Validators.required]],
      net: ['', [Validators.required]],
      description: ['', [Validators.required]],
      categoryId: [null, [Validators.required]]
    });
    
       
        this.getCategories();
  }


  // Metodo para listar los productos
  getServices(){
    this.loading = true; 
    this.serviceService.getAllServices().subscribe({
      next: (result) => {
        this.services = result;
        this.loading = false; 
      },
      error: (err) => {
        console.log(err);
      }
    })
  }



  // Inicializar el formulario sin validaciones
  initializeForm() {
    this.selectedProductForm = this.formBuilder.group({
      name: [''],
      net: [''],
      description: [''],
      categoryId: ['']
    });
  }


  
  // Método trackBy para mejorar la eficiencia al listar los clientes
  trackByCustomerId(index: number, product: ServiceInterface): number {
    return product.productId;
  }


  // Metodo para el modal de boostrap
  openDeleteModal(id: number): void {
    this.productToDelete = id;
  }




  // Metodo para eliminar producto
  deleteService(): void {
    if (this.productToDelete !== null) {
      this.serviceService.deleteService(this.productToDelete).subscribe({
        next: () => {
          console.log('Cliente eliminado con éxito');
          this.getServices(); 
          this.productToDelete = null; 
        },
        error: (error) => {
          console.error('Error al eliminar el cliente', error);
        }
      });
    }
  }

   


  // Metodo para listar las categorias
   getCategories(): void {
    this.categoryService.getCategories().subscribe((data: Category[]) => {
      this.categories = data;
    });
  }





  // Metodo para mostrar el producto seleccionado
  selectService(service: ServiceInterface): void {
    // Aquí, se asignan los valores del servicio seleccionado al formulario reactivo.
    this.serviceForm.patchValue({
      productId: service.productId,
      name: service.name,
      net: service.net,
      description: service.description,
      categoryId: this.getCategoryIdFromName(service.category) // Convertir el nombre de la categoría en su ID.
    });
  }
  




  // Método para convertir el nombre de la categoría en su ID.
  getCategoryIdFromName(categoryName: string): number {
    const category = this.categories.find(cat => cat.name === categoryName);
    return category ? category.categoryId : 0; // Devuelve el ID de la categoría o 0 si no se encuentra.
  }




  
  
  // Metodo para Actualizar un producto
  updateService(): void {
    if (this.serviceForm.valid) {
      const updatedService = this.serviceForm.value; // Obtenemos los valores del formulario.
      console.log('Datos para actualizar:', updatedService);
  
      // Asegúrate de que `productId` esté presente.
      if (!updatedService.productId) {
        console.error('El ID del servicio no está definido.');
        return;
      }
  
      this.serviceService.updateService(updatedService.productId, updatedService).subscribe({
        next: (data) => {
          console.log('Servicio actualizado con éxito', data);
          this.getServices(); // Recargar la lista de servicios.
          this.serviceForm.reset(); // Limpiar el formulario.
        },
        error: (error) => {
          console.error('Error al actualizar el servicio', error);
        }
      });
    }
  }


    // Realizar la búsqueda en el backend
    searchServices(): void {
      if (this.searchTerm.trim() === '') {
        this.getServices(); // Si el campo de búsqueda está vacío, cargar todos los clientes
        return;
      }
  
      this.loading = true;
      this.serviceService.searchServiceByName(this.searchTerm).subscribe({
        next: (data) => {
          this.services = data;
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
      this.searchServices(); // Ejecuta la búsqueda cuando se envía el formulario
    }

}
