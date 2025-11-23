import { Injectable } from '@angular/core';
import { StateInterface } from '../interfaces/state.interface';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StateService {

    private apiUrl = 'http://localhost:8080/arky/app/state';

  constructor(private http: HttpClient) { }


  getAllStates(): Observable<StateInterface[]> {
    return this.http.get<StateInterface[]>(`${this.apiUrl}/all`);
  }



}
