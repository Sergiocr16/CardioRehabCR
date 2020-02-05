import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { IMinorEvent } from 'app/shared/model/minor-event.model';
import { MinorEventService } from 'app/entities/minor-event/minor-event.service';
import { ModalService } from 'app/shared/util/modal.service';
import { GlobalVariablesService } from 'app/shared/util/global-variables.service';
import { MayorEventService } from 'app/entities/mayor-event/mayor-event.service';
import { IMayorEvent } from 'app/shared/model/mayor-event.model';

@Component({
  selector: 'jhi-rehabilitation-group-detail',
  templateUrl: './rehabilitation-group-detail.component.html'
})
export class RehabilitationGroupDetailPanelDataComponent implements OnInit {
  minorEvents: IMinorEvent[];
  mayorEvents: IMayorEvent[];
  eventsGraph: IMinorEvent[];
  displayEventsSessionsGraph = false;
  barChartEventsPerSessionDistribution;

  barChartEventsPerSessionDistributionLabels;
  barChartEventsPerSessionDistributionType;
  barChartEventsPerSessionDistributionLegend;
  barChartEventsPerSessionDistributionColors;
  barChartOptionsEventsPerSessionDistribution;
  barChartDataEventsPerSessionDistribution;

  rehabilitationGroup: any;
  barChartOptionsStadistics;
  barChartLabelsStadistics;
  barChartTypeStadistics;
  barChartLegendStadistics = true;
  barChartDataStadistics;
  lineChartColorsStadistics;
  barChartOptionsDistribution;
  barChartLabelsDistribution;
  barChartTypeDistribution;
  barChartLegendDistribution = true;
  barChartDataDistribution;
  lineChartColorsDistribution = [];
  step = 0;
  selectedSesion;
  eventType = '1';
  labelEventType = 'Seleccione el evento mayor';
  eventPerSessionId;
  graphicEventsPerSessions = [];

  barChartOptionsSessionMinor;
  barChartLabelsSessionMinor;
  barChartTypeSessionMinor;
  barChartLegendSessionMinor = false;
  barChartDataSessionMinor;
  lineChartColorsSessionMinor = [];

  barChartOptionsSessionMayor;
  barChartLabelsSessionMayor;
  barChartTypeSessionMayor;
  barChartLegendSessionMayor = false;
  barChartDataSessionMayor;
  lineChartColorsSessionMayor = [];
  // barChartOptions: ChartOptions = {
  //   responsive: true,
  //   // We use these empty structures as placeholders for dynamic theming.
  //   scales: {xAxes: [{}], yAxes: [{}]},
  //   plugins: {
  //     datalabels: {
  //       anchor: 'end',
  //       align: 'end',
  //     }
  //   }
  // };
  // barChartLabels: Label[] = ['2006', '2007', '2008', '2009', '2010', '2011', '2012'];
  // barChartType: ChartType = 'bar';
  // barChartLegend = true;
  // barChartData: ChartDataSets[] = [
  //   {data: [65, 59, 80, 81, 56, 55, 40], label: 'Series A'},
  //   {data: [28, 48, 40, 19, 86, 27, 90], label: 'Series B'}
  // ];
  constructor(
    protected activatedRoute: ActivatedRoute,
    protected minorEventService: MinorEventService,
    protected mayorEventService: MayorEventService,
    protected modal: ModalService,
    private global: GlobalVariablesService
  ) {}

  renderGraphMinorDistribution(rehabilitationGroup) {
    this.barChartOptionsDistribution = {
      responsive: true,
      // We use these empty structures as placeholders for dynamic theming.
      scales: { xAxes: [{}], yAxes: [{}] },
      plugins: {
        datalabels: {
          anchor: 'end',
          align: 'end'
        }
      }
    };
    this.barChartLabelsDistribution = [];
    this.barChartTypeDistribution = 'line';
    this.barChartLegendDistribution = true;
    this.lineChartColorsDistribution = [
      {
        borderColor: '#8c24a8',
        backgroundColor: 'rgba(140, 36, 168, 0.52)'
      },
      {
        borderColor: '#f44336',
        backgroundColor: 'rgba(244, 64, 52, 0.58)'
      }
    ];
    const minorEvents = { data: [], label: 'Eventos menores' };
    const mayorEvents = { data: [], label: 'Eventos mayores' };
    for (let i = 0; i < 60; i++) {
      this.barChartLabelsDistribution.push(i + 1 + '');
      minorEvents.data.push(rehabilitationGroup.panelData.distributionMinorEvents[i].minorEventsPerSesion);
      mayorEvents.data.push(rehabilitationGroup.panelData.distributionMinorEvents[i].mayorEventsPerSesion);
    }
    this.barChartDataDistribution = [minorEvents, mayorEvents];
  }

  selectEventType(e) {
    this.labelEventType = this.eventType === '1' ? 'Seleccione el evento mayor' : 'Seleccione el evento menor';
    this.graphicEventsPerSessions = this.eventType === '1' ? this.mayorEvents : this.minorEvents;
    this.displayEventsSessionsGraph = false;
    this.eventPerSessionId = undefined;
  }

  showGraphEventPerSessions(e) {
    if (this.eventType === '2') {
      this.minorEventService
        .graphicMinorEventSession({
          groupId: this.rehabilitationGroup.id,
          rehabilitationId: this.global.rehabCenter,
          minorEventId: this.eventPerSessionId
        })
        .subscribe((res: HttpResponse<[]>) => this.showGraphEventPerSessionsRenderMinor(res.body, res.headers));
    } else {
      this.mayorEventService
        .graphicMayorEventSession({
          groupId: this.rehabilitationGroup.id,
          rehabilitationId: this.global.rehabCenter,
          mayorEventId: this.eventPerSessionId
        })
        .subscribe((res: HttpResponse<[]>) => this.showGraphEventPerSessionsRenderMayor(res.body, res.headers));
    }
  }

  protected findEvent(id: any) {
    if (this.eventType === '1') {
      for (const event of this.mayorEvents) {
        if (event.id === id) {
          return event.description;
        }
      }
    } else {
      for (const event of this.minorEvents) {
        if (event.id === id) {
          return event.description;
        }
      }
    }
  }
  protected showGraphEventPerSessionsRenderMinor(data: IMinorEvent[], headers: HttpHeaders) {
    this.eventsGraph = data;
    this.barChartOptionsEventsPerSessionDistribution = {
      responsive: true,
      // We use these empty structures as placeholders for dynamic theming.
      scales: { xAxes: [{}], yAxes: [{}] },
      plugins: {
        datalabels: {
          anchor: 'end',
          align: 'end'
        }
      }
    };
    this.barChartEventsPerSessionDistributionLabels = [];
    this.barChartEventsPerSessionDistributionType = 'line';
    this.barChartEventsPerSessionDistributionLegend = true;
    this.barChartEventsPerSessionDistributionColors = [
      {
        borderColor: '#33a88c',
        backgroundColor: 'rgba(56,168,142,0.52)'
      }
    ];
    const events = { data: [], label: this.findEvent(this.eventPerSessionId) };
    for (let i = 0; i < 60; i++) {
      this.barChartEventsPerSessionDistributionLabels.push(i + 1 + '');
      events.data.push(parseInt(this.eventsGraph[i].description, 10));
    }
    this.barChartDataEventsPerSessionDistribution = [events];
    this.displayEventsSessionsGraph = true;
  }
  protected showGraphEventPerSessionsRenderMayor(data: IMayorEvent[], headers: HttpHeaders) {
    this.eventsGraph = data;
    this.barChartOptionsEventsPerSessionDistribution = {
      responsive: true,
      // We use these empty structures as placeholders for dynamic theming.
      scales: { xAxes: [{}], yAxes: [{}] },
      plugins: {
        datalabels: {
          anchor: 'end',
          align: 'end'
        }
      }
    };
    this.barChartEventsPerSessionDistributionLabels = [];
    this.barChartEventsPerSessionDistributionType = 'line';
    this.barChartEventsPerSessionDistributionLegend = true;
    this.barChartEventsPerSessionDistributionColors = [
      {
        borderColor: '#33a88c',
        backgroundColor: 'rgba(56,168,142,0.52)'
      }
    ];
    const events = { data: [], label: this.findEvent(this.eventPerSessionId) };
    for (let i = 0; i < 60; i++) {
      this.barChartEventsPerSessionDistributionLabels.push(i + 1 + '');
      events.data.push(parseInt(this.eventsGraph[i].description, 10));
    }
    this.barChartDataEventsPerSessionDistribution = [events];
    this.displayEventsSessionsGraph = true;
  }
  selectSesion(e) {
    const session = this.rehabilitationGroup.panelData.distributionMinorEvents[this.selectedSesion];
    this.barChartOptionsSessionMinor = {
      responsive: true,
      legend: {
        position: 'left'
      },
      plugins: {
        datalabels: {}
      }
    };
    this.barChartTypeSessionMinor = 'pie';
    this.barChartLegendSessionMinor = true;

    this.barChartDataSessionMinor = [];
    this.barChartLabelsSessionMinor = [];
    for (let j = 0; j < session.minorEvents.length; j++) {
      const minorEvent = session.minorEvents[j];
      this.barChartLabelsSessionMinor.push(minorEvent.description);
      this.barChartDataSessionMinor.push(minorEvent.id);
    }

    this.barChartOptionsSessionMayor = {
      responsive: true,
      legend: {
        position: 'left'
      },
      plugins: {
        datalabels: {}
      }
    };
    this.barChartLabelsSessionMayor = [];
    this.barChartTypeSessionMayor = 'pie';
    this.barChartLegendSessionMayor = false;
    this.barChartDataSessionMayor = [];
    this.barChartLabelsSessionMayor = [];
    for (let j = 0; j < session.mayorEvents.length; j++) {
      const mayorEvent = session.mayorEvents[j];
      this.barChartLabelsSessionMayor.push(mayorEvent.description);
      this.barChartDataSessionMayor.push(mayorEvent.id);
    }
  }

  renderGraphStadisctics(rehabilitationGroup) {
    this.barChartOptionsStadistics = {
      responsive: true
      // // We use these empty structures as placeholders for dynamic theming.
      // scales: {xAxes: [{}], yAxes: [{}]},
      // plugins: {
      //   datalabels: {
      //     anchor: 'end',
      //     align: 'end',
      //   }
      // }
    };
    this.lineChartColorsStadistics = [
      {
        backgroundColor: 'rgba(0, 138, 124, 0.63)'
      }
    ];
    this.barChartLabelsStadistics = [
      'Abandono por causa no médica',
      'Mejoría en su capacidad funcional',
      'Mejoría en su control perfil lipídico',
      'Mejoría en su control glicémico',
      'Reducción de 5% de peso basal',
      'Suspendieron tabaquismo',
      'Reincorporan a la actividad laboral',
      'Adherencia al ejercicio físico'
    ];

    this.barChartTypeStadistics = 'horizontalBar';
    this.barChartLegendStadistics = true;
    this.barChartDataStadistics = [
      {
        data: [
          rehabilitationGroup.panelData.abandonmentNonMedicalCausePercentage,
          rehabilitationGroup.panelData.improvementFunctionalCapacityPercentage,
          rehabilitationGroup.panelData.improvementLipidicProfilePercentage,
          rehabilitationGroup.panelData.improvementGlycemicControlPercentage,
          rehabilitationGroup.panelData.weightReductionPercentage,
          rehabilitationGroup.panelData.suspendedSmokingPercentage,
          rehabilitationGroup.panelData.returnWorkActivityPercentage,
          rehabilitationGroup.panelData.exerciseAdherenceRestPercentage
        ],
        label: 'Porcentaje de pacientes'
      }
    ];
  }

  setStep(index: number) {
    this.step = index;
  }

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ rehabilitationGroup }) => {
      this.rehabilitationGroup = rehabilitationGroup;
      this.renderGraphStadisctics(this.rehabilitationGroup);
      this.renderGraphMinorDistribution(this.rehabilitationGroup);
      this.loadAllMinorEvents();
      this.loadAllMayorEvents();
    });
  }

  loadAllMinorEvents() {
    this.minorEventService
      .query({
        page: 0,
        size: 1000,
        rehabilitationId: this.global.rehabCenter
      })
      .subscribe((res: HttpResponse<[]>) => this.paginateMinorEvents(res.body, res.headers));
  }
  protected paginateMinorEvents(data: [], headers: HttpHeaders) {
    this.minorEvents = [];
    for (let i = 0; i < data.length; i++) {
      this.minorEvents.push(data[i]);
    }
  }

  loadAllMayorEvents() {
    this.mayorEventService
      .query({
        page: 0,
        size: 1000,
        rehabilitationId: this.global.rehabCenter
      })
      .subscribe((res: HttpResponse<[]>) => this.paginateMayorEvents(res.body, res.headers));
  }
  protected paginateMayorEvents(data: [], headers: HttpHeaders) {
    this.mayorEvents = [];
    for (let i = 0; i < data.length; i++) {
      this.mayorEvents.push(data[i]);
    }
    this.graphicEventsPerSessions = this.mayorEvents;
  }
  previousState() {
    window.history.back();
  }
}
