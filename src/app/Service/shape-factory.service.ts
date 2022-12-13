import { Injectable } from '@angular/core';
import { Dto } from '../drawing-space/dto';
import { DtoAdapterService } from './dto-adapter.service';
import { KonvaService } from './konva.service';
import { ShapesService } from './shapes.service';

@Injectable({
  providedIn: 'root'
})
export class ShapeFactoryService {

  constructor(private myKonvaService: KonvaService, private reqService: ShapesService){}

  createShape(shape: string){
      let created: any;
      switch (shape.toLowerCase()){
        case 'circle':
            created = this.myKonvaService.circle();
            break;

          case 'square':
            created = this.myKonvaService.square();
            break;

          case 'rect':
            created = this.myKonvaService.rect();
            break;

          case 'triangle':
            created = this.myKonvaService.triangle();
            break;

          case 'ellipse':
            created = this.myKonvaService.ellipse();
            break;

          case 'line':
            created = this.myKonvaService.line();
            break;
          default:
            console.log('ERROR shape not defined ', shape);
        }

      return created;
    }
}
