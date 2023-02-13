import {Component} from '@angular/core';
import {PlannerService} from '../../../services/planner.service';
import {MatSnackBar} from "@angular/material/snack-bar";
import Utils from "../../../services/utils";

@Component({
  selector: 'app-planner',
  templateUrl: './planner.component.html',
  styleUrls: ['./planner.component.css']
})
export class PlannerComponent {

  pensioenWaarde: string = '';

  constructor(private plannerService: PlannerService, private _snackBar: MatSnackBar) {
  }

  submitPlanner(data: { email: string, pensioenleeftijd: number }) {
    this.plannerService.getVerwachteWaarde(data.email, data.pensioenleeftijd).subscribe(
      response => this.pensioenWaarde = Utils.formatterNumberToEuro(response),
      error => {
        this.pensioenWaarde = ''
        const message = typeof error.error === "string" ? error.error : error.message
        this.errorSnackBar(message)
      }
    )
  }

  errorSnackBar(message: string) {
    this._snackBar.open(message, 'Close', {
      duration: 2000
    });
  }
}
