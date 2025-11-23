import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, tap } from 'rxjs';
import { RegisterInterface } from '../interfaces/register.interface';
import { LoginInterface } from '../interfaces/login.interface';

@Injectable({
  providedIn: 'root'
})
export class AuthService {


  private apiUrl = 'http://localhost:8080/arky/app/auth';

  constructor(private http: HttpClient, private router: Router) { }

    // Método para registrar un usuario
    register(userData: any): Observable<RegisterInterface> {
      return this.http.post<RegisterInterface>(`${this.apiUrl}/register`, userData);
    }
  
    // Método para hacer login
    login(credentials: any): Observable<LoginInterface> {
      return this.http.post<LoginInterface>(`${this.apiUrl}/login`, credentials).pipe(
        tap((response: any) => {
          // Guardar el token JWT en el localStorage
          this.setToken(response.token);
          // Guardar el nombre del usuario en el localStorage
          localStorage.setItem('firstname', response.firstName);
          localStorage.setItem('lastname', response.lastName);
          localStorage.setItem('userId', response.userId);
        })
      );
    }
  
    // Guardar token JWT en el localStorage
    setToken(token: string): void {
      localStorage.setItem('jwtToken', token);
    }
  
    // Obtener token JWT del localStorage
    getToken(): string | null {
      return localStorage.getItem('jwtToken');
    }
  
    // Método para verificar si el usuario está logeado
    isLoggedIn(): boolean {
      return !!this.getToken();
    }
  
    // Método para cerrar sesión
    logout(): void {
      localStorage.removeItem('jwtToken');
      localStorage.removeItem('firstname');
      localStorage.removeItem('lastname');
      localStorage.removeItem('userId');

      this.router.navigate(['/login']);
    }

 
  // Método para obtener el nombre del usuario
  setFirstName(firstname: string): void {
    localStorage.setItem('firstname', firstname);
  }

  setLastName(lastname: string): void {
    localStorage.setItem('lastname', lastname);
  }

  setUserId(userId: string): void {
    localStorage.setItem('userId', userId);
  }


    // Método para obtener el nombre del usuario
    getFirstName(): string | null {
      return localStorage.getItem('firstname');
    }
  
    getLastName(): string | null {
      return localStorage.getItem('lastname');
    }

    getUserId(): string | null {
      return localStorage.getItem('userId');
    }


}
