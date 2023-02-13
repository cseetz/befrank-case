import {NgModule} from '@angular/core';

import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatInputModule} from "@angular/material/input";
import {MatCardModule} from "@angular/material/card";
import {MatGridListModule} from "@angular/material/grid-list";
import {MatSnackBarModule} from "@angular/material/snack-bar";

const material = [
  MatToolbarModule,
  MatButtonModule,
  MatInputModule,
  MatCardModule,
  MatGridListModule,
  MatSnackBarModule
]

@NgModule({
  imports: [material],
  exports: [material]
})
export class MaterialModule {
}
