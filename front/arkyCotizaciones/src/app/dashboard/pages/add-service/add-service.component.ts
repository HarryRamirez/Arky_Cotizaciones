import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { ServiceService } from '../../../services/service.service';
import { Service } from '../../../interfaces/tipo.interface';
import { Category } from '../../../interfaces/category.interface';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { CategoryService } from '../../../services/category.service';
import { Modal } from 'bootstrap';

@Component({
  selector: 'app-add-service',
  standalone: true,
  imports: [RouterModule, ReactiveFormsModule],
  templateUrl: './add-service.component.html',
  styleUrl: './add-service.component.scss'
})
export default class AddServiceComponent implements OnInit{
  serviceForm: FormGroup;
  categories: Category[] = [];

  constructor(
    private fb: FormBuilder,
    private categoryService: CategoryService,
    private serviceService: ServiceService,
    private router: Router
  ) {}

  ngOnInit(): void {
    // Inicializar el formulario con los campos necesarios
    this.serviceForm = this.fb.group({
      name: ['', Validators.required],
      net: ['', Validators.required],
      description: ['', Validators.required],
      categoryId: ['', Validators.required]  // Campo para el select
    });

        // Cargar las categorías para mostrarlas en el select
        this.getCategories();
  }


  // Método para obtener las categorías de la base de datos
  getCategories(): void {
    this.categoryService.getCategories().subscribe((data: Category[]) => {
      this.categories = data;
    });
  }


  

  // Método para guardar el producto
  saveService(): void {
    if (this.serviceForm.valid) {
      const newService: Service = {
        ...this.serviceForm.value,
        categoryId: this.serviceForm.value.categoryId
      };

      this.serviceService.saveService(newService).subscribe({
        next: (data) => {
          console.log('Servicio guardado con éxito', data);

                    // Mostrar el modal de éxito
                    const modalElement = document.getElementById('staticBackdrop');
                    const modal = new Modal(modalElement!);
                    modal.show();
          
                    // Ocultar el modal después de 3 segundos
                    setTimeout(() => {
                      modal.hide();
                    }, 2000);

          this.serviceForm.reset();  // Limpiar el formulario después de guardar
        },
        error: (error) => {
          console.error('Error al guardar el servicio', error);
        }
      });
    }
  }


  navigateAndRefresh() {
    this.router.navigate(['/dashboard/service']).then(() => {
      window.location.reload();  // Refresca la página
    });
  }

}
