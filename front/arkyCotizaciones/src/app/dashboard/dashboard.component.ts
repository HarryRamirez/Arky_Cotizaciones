import { Component } from '@angular/core';
import { Router, RouterModule, RouterOutlet } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [RouterModule, RouterOutlet],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export default class DashboardComponent {

  
  firstName: string |  null;
  lastName: string | null;

  constructor(private router: Router, private authService: AuthService) { 
    this.firstName = localStorage.getItem('firstname');
    this.lastName = localStorage.getItem('lastname');
  }

  ngOnInit(): void {

  }


  // Metodo para cerrar sesion
  logout(): void {
    this.authService.logout(); 
    this.router.navigate(['/login']); 
  }
  
}
