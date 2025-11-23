import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ClientInterface } from '../interfaces/client.interface';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  private apiUrl = 'http://localhost:8080/arky/app/customer';


  constructor(private http: HttpClient) { }

  
  getAllClients(): Observable<ClientInterface[]> {
    return this.http.get<ClientInterface[]>(`${this.apiUrl}/all`);
  }

  getClientById(id: number): Observable<ClientInterface> {
    return this.http.get<ClientInterface>(`${this.apiUrl}/${id}`);
}

saveClient(client: ClientInterface): Observable<ClientInterface> {
  return this.http.post<ClientInterface>(`${this.apiUrl}/save`, client);
}

updateClient(customerId: number, client: ClientInterface): Observable<ClientInterface> {
  return this.http.put<ClientInterface>(`${this.apiUrl}/update/${customerId}`, client);
}

deleteClient(id: number): Observable<void> {
  return this.http.delete<void>(`${this.apiUrl}/delete/${id}`);
}



searchCustomersByName(name: string): Observable<ClientInterface[]> {
  return this.http.get<ClientInterface[]>(`${this.apiUrl}/search?name=${name}`);
}


  // Método para hacer la petición HTTP y descargar el archivo Excel
  ExcelReport(): Observable<Blob> {
    return this.http.get(`${this.apiUrl}/excel`, { responseType: 'blob' });
  }

}
