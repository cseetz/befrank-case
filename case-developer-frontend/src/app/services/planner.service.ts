import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Belegging} from "../model/belegging";

@Injectable({
  providedIn: 'root'
})

export class PlannerService {

  private url = 'http://localhost:8080/case';

  constructor(private http: HttpClient) {
  }

  getBeleggingen(rekeningNr: string) {
    return this.http.get<Array<Belegging>>(`${this.url}/rekening/${rekeningNr}`);
  }

  getVerwachteWaarde(email: string, pensioenleeftijd: number) {
    return this.http.get<number>(`${this.url}/waarde/${email}/${pensioenleeftijd}`);
  }
}
