package SEBAS.core

trait Situation

trait Effect

abstract class Agent[ActionType<:Action[EffectType,_],EffectType<:Effect,SituationType<:Situation]{
    var situation:SituationType
    def be_affected(e:EffectType):Agent[ActionType,EffectType,SituationType]
    def decide():ActionType
}

abstract class Action[EffectType<:Effect,MediaType<:Medium[_,EffectType]]{
    def happen(m:Set[MediaType]):Unit
}

abstract class Plan[ActionType<:Action[_,MediumType],MediumType<:Medium[_,_]]{
    var media:Set[MediumType] = Set.empty
    def schedule(a:ActionType):Plan[ActionType,MediumType]
    def resolve():Plan[ActionType,MediumType]
}

abstract class Medium[AgentType<:Agent[_,EffectType,_],EffectType<:Effect]{
    var agents:Set[AgentType] = Set.empty
}
