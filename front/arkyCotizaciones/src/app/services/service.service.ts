import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ServiceInterface } from '../interfaces/service.interface';
import { Service } from '../interfaces/tipo.interface';

@Injectable({
  providedIn: 'root'
})
export class ServiceService {

  private apiUrl = "http://localhost:8080/arky/app/service"

  constructor(private http: HttpClient) { }

  

  getAllServices(): Observable<ServiceInterface[]> {
    return this.http.get<ServiceInterface[]>(`${this.apiUrl}/all`);
  }

  updateService(serviceId: number, updatedService: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/update/${serviceId}`, updatedService);
  }

  saveService(service: Service): Observable<Service> {
    return this.http.post<Service>(`${this.apiUrl}/save`, service);
  }

  deleteService(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${id}`);
  }

  searchServiceByName(name: string): Observable<ServiceInterface[]> {
    return this.http.get<ServiceInterface[]>(`${this.apiUrl}/search?name=${name}`);
  }
}
