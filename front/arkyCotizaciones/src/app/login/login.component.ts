import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { LoginInterface } from '../interfaces/login.interface';



@Component({
  selector: 'app-login',
  standalone: true,
  imports: [RouterModule, FormsModule, ReactiveFormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export default class LoginComponent implements OnInit{
  passwordVisible: boolean = false;

  loginForm: FormGroup;

  constructor(private authService: AuthService, private router: Router, private fb: FormBuilder,) { }
    
  ngOnInit(): void {
    
    this.loginForm = this.fb.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
    });
  }
  
  // Metodo para hacer visible la contraseña
  togglePasswordVisibility(): void {
    this.passwordVisible = !this.passwordVisible;
  }





  
  // Metodo de inicio de sesion
  login(): void {
    if (this.loginForm.valid) {
      const credentials: LoginInterface = this.loginForm.value; // Recolectar los datos del formulario
  
      this.authService.login(credentials).subscribe({
        next: (response: any) => {
          // Guardar el token JWT en el localStorage
          this.authService.setToken(response.token);
          this.authService.setFirstName(response.firstname);
          this.authService.setLastName(response.lastname);
         
          console.log('Token recibido desde el backend:', response.token); // Token desde el backend
          console.log('Token guardado en localStorage:', localStorage.getItem('jwtToken'));


  
          // Redirigir al dashboard después de iniciar sesión
          this.router.navigate(['/dashboard/home']);
        },
        error: (error) => {
          console.error('Error en el inicio de sesión', error);
          alert('El usuario no está registrado');
        },
        complete: () => {
          console.log('Proceso de inicio de sesión completo.');
        }
      });
    } else {
      alert('Por favor, complete todos los campos');
    }
  }
}
