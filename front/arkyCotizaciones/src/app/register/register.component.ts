import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { RegisterInterface } from '../interfaces/register.interface';
import { Modal } from 'bootstrap';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [RouterModule, FormsModule, ReactiveFormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export default class RegisterComponent implements OnInit{

  registerForm: FormGroup;


  passwordVisible: boolean = false;

  constructor(private authService: AuthService, private router: Router, private fb: FormBuilder,) { }
  
  
  ngOnInit(): void {
    
    this.registerForm = this.fb.group({
      firstname: ['', Validators.required],
      lastname: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', Validators.required],
      role: ['', Validators.required]
    });
  }


  // Metodo para hacer la contraseña visible
  togglePasswordVisibility(): void {
    this.passwordVisible = !this.passwordVisible;
  }




  
// Método para registrar el usuario
register(): void {
  if (this.registerForm.valid) {
    const newRegister: RegisterInterface = {
      ...this.registerForm.value,
      roleId: this.registerForm.value.role
    };

    console.log("Datos enviados al backend:", newRegister);  // Aquí verificamos qué se está enviando

    this.authService.register(newRegister).subscribe({
      next: (data) => {
        console.log('Usuario guardado con éxito', data);

                  // Mostrar el modal de éxito
                  const modalElement = document.getElementById('staticBackdrop');
                  const modal = new Modal(modalElement!);
                  modal.show();
        
                  // Ocultar el modal después de 3 segundos
                  setTimeout(() => {
                    modal.hide();
                  }, 2000);

        this.registerForm.reset();  // Limpiar el formulario después de guardar
        this.router.navigate(['/login']);
      },
      error: (error) => {
        console.error('Error al guardar el Usuario', error);
      }
    });
    }
  }
}
