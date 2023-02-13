import {Component} from '@angular/core';
import {PlannerService} from "../../../services/planner.service";
import {MatSnackBar} from "@angular/material/snack-bar";
import {Belegging} from "../../../model/belegging";
import Utils from "../../../services/utils";

@Component({
  selector: 'app-overzicht',
  templateUrl: './overzicht.component.html',
  styleUrls: ['./overzicht.component.css']
})
export class OverzichtComponent {

  beleggingen: Array<Belegging> = [];

  constructor(private plannerService: PlannerService, private _snackBar: MatSnackBar) {
  }

  getTotaleBeleggingsWaarde() {
    const som = this.beleggingen.reduce((som, belegging) => som + belegging.waarde, 0);
    return this.formatterNumberToEuro(som);
  }

  submitRekening(data: { nummer: string }) {
    this.plannerService.getBeleggingen(data.nummer).subscribe(
      response => this.beleggingen = response,
      error => {
        this.beleggingen = []
        const message = typeof error.error === "string" ? error.error : error.message
        this.errorSnackBar(message)
      }
    )
  }

  formatterNumberToEuro(waarde: number) {
    return Utils.formatterNumberToEuro(waarde);
  }

  errorSnackBar(message: string) {
    this._snackBar.open(message, 'Close', {
      duration: 2000
    });
  }
}
